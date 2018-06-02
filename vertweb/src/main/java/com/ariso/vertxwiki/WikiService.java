package com.ariso.vertxwiki;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ariso.vertxstudy.todolist.TodoEntity;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.guides.wiki.MainVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;

/*
 *  provide database access for wiki services
 */
public class WikiService {

    private final Vertx         vertx;
    private final JsonObject    config;
    private final JDBCClient    client;
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiService.class);

    public WikiService(Vertx vertx, JsonObject config) {
        this.vertx = vertx;
        this.config = config;
        this.client = JDBCClient.createShared(vertx, config);
    }

    public Completable initData() {
        return client.rxGetConnection().flatMapCompletable(
                connection -> connection.rxExecute(SQL_CREATE_PAGES_TABLE).doOnTerminate(connection::close));
    }

    // public Single<WikiPageEntity> insert(WikiPageEntity todo) {
    // // JsonArray params = new
    // // JsonArray().add(todo.getId()).add(todo.getTitle()).add(todo.isCompleted())
    // // .add(todo.getOrder()).add(todo.getUrl());
    // // return client.rxUpdateWithParams(SQL_INSERT, params).map(e -> todo);
    // }
    //
    // public Single<List<WikiPageEntity>> getAll() {
    // // return client.rxQuery(SQL_QUERY_ALL)
    // // .map(ar ->
    // // ar.getRows().stream().map(TodoEntity::new).collect(Collectors.toList()));
    // }
    //
    // public Maybe<WikiPageEntity> getCertain(String todoID) {
    // // return client.rxQueryWithParams(SQL_QUERY, new
    // // JsonArray().add(todoID)).map(ResultSet::getRows).toObservable()
    // // .flatMapIterable(e -> e).singleElement().map(TodoEntity::new);
    // }
    //
    // public Maybe<WikiPageEntity> update(String todoId, WikiPageEntity newTodo) {
    // // return getCertain(todoId).flatMap(old -> {
    // // TodoEntity fnTodo = old.merge(newTodo);
    // // int updateId = old.getId();
    // // JsonArray params = new
    // // JsonArray().add(updateId).add(fnTodo.getTitle()).add(fnTodo.isCompleted())
    // // .add(fnTodo.getOrder()).add(fnTodo.getUrl()).add(updateId);
    // // return client.rxUpdateWithParams(SQL_UPDATE, params).flatMapMaybe(v ->
    // // Maybe.just(fnTodo));
    // // });
    // }

    public Single<WikiPageEntity> insert(WikiPageEntity entity) {

        JsonArray params = new JsonArray().add(entity.getId()).add(entity.getTitle()).add(entity.getMarkdownText());
        return client.rxUpdateWithParams(SQL_INSERT, params).map(e -> entity);
    }

    public Completable delete(String todoId) {
        return client.rxUpdateWithParams(SQL_DELETE_PAGE, new JsonArray().add(todoId)).toCompletable();
    }

    // public Completable deleteAll() {
    // // return client.rxUpdate(SQL_DELETE_ALL).toCompletable();
    // }

    private static final String SQL_CREATE_PAGES_TABLE = "create table if not exists Pages "
            + "(Id integer identity primary key, " + "Name varchar(255) unique, " + "Content clob)";

    private static final String SQL_GET_PAGE           = "select Id, Content from Pages where Name = ?"; // <1>
    private static final String SQL_INSERT             = "insert into Pages values (NULL, ?, ?)";
    private static final String SQL_SAVE_PAGE          = "update Pages set Content = ? where Id = ?";
    private static final String SQL_ALL_PAGES          = "select Name from Pages";
    private static final String SQL_DELETE_PAGE        = "delete from Pages where Id = ?";

}
