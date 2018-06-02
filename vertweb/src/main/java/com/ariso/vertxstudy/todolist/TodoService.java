package com.ariso.vertxstudy.todolist;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;

/**
 * Reactive service interface of todo backend.
 *
 * @author Eric Zhao
 */
public class TodoService {

    private final Vertx      vertx;
    private final JsonObject config;
    private final JDBCClient client;

    public TodoService(Vertx vertx, JsonObject config) {
        this.vertx = vertx;
        this.config = config;
        this.client = JDBCClient.createShared(vertx, config);
    }

    public Completable initData() {
        return client.rxGetConnection()
                .flatMapCompletable(connection -> connection.rxExecute(SQL_CREATE).doOnTerminate(connection::close));
    }

    public Single<TodoEntity> insert(TodoEntity todo) {
        JsonArray params = new JsonArray().add(todo.getId()).add(todo.getTitle()).add(todo.isCompleted())
                .add(todo.getOrder()).add(todo.getUrl());
        return client.rxUpdateWithParams(SQL_INSERT, params).map(e -> todo);
    }

    public Single<List<TodoEntity>> getAll() {
        return client.rxQuery(SQL_QUERY_ALL)
                .map(ar -> ar.getRows().stream().map(TodoEntity::new).collect(Collectors.toList()));
    }

    public Maybe<TodoEntity> getCertain(String todoID) {
        return client.rxQueryWithParams(SQL_QUERY, new JsonArray().add(todoID)).map(ResultSet::getRows).toObservable()
                .flatMapIterable(e -> e).singleElement().map(TodoEntity::new);
    }

    public Maybe<TodoEntity> update(String todoId, TodoEntity newTodo) {
        return getCertain(todoId)
                .flatMap(old -> {
                    TodoEntity fnTodo = old.merge(newTodo);
                    int updateId = old.getId();
                    JsonArray params = new JsonArray()
                            .add(updateId)
                            .add(fnTodo.getTitle())
                            .add(fnTodo.isCompleted())
                            .add(fnTodo.getOrder())
                            .add(fnTodo.getUrl())
                            .add(updateId);
                    return client.rxUpdateWithParams(SQL_UPDATE, params).flatMapMaybe(v -> Maybe.just(fnTodo));
                });
    }

    public Completable delete(String todoId) {
        return client.rxUpdateWithParams(SQL_DELETE, new JsonArray().add(todoId)).toCompletable();
    }

    public Completable deleteAll() {
        return client.rxUpdate(SQL_DELETE_ALL).toCompletable();
    }

    private static final String SQL_CREATE     = "CREATE TABLE IF NOT EXISTS `todo` (\n"
            + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" + "  `title` varchar(255) DEFAULT NULL,\n"
            + "  `completed` tinyint(1) DEFAULT NULL,\n" + "  `order` int(11) DEFAULT NULL,\n"
            + "  `url` varchar(255) DEFAULT NULL,\n" + "  PRIMARY KEY (`id`) )";
    private static final String SQL_INSERT     = "INSERT INTO `todo` "
            + "(`id`, `title`, `completed`, `order`, `url`) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_QUERY      = "SELECT * FROM todo WHERE id = ?";
    private static final String SQL_QUERY_ALL  = "SELECT * FROM todo";
    private static final String SQL_UPDATE     = "UPDATE `todo`\n" + "SET\n" + "`id` = ?,\n" + "`title` = ?,\n"
            + "`completed` = ?,\n" + "`order` = ?,\n" + "`url` = ?\n" + "WHERE `id` = ?;";
    private static final String SQL_DELETE     = "DELETE FROM `todo` WHERE `id` = ?";
    private static final String SQL_DELETE_ALL = "DELETE FROM `todo`";

}
