package com.ariso.vertxstudy.todolist;

import java.util.concurrent.atomic.AtomicInteger;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Todo Entity
 */
@DataObject(generateConverter = true)
public class TodoEntity {

    private static final AtomicInteger acc = new AtomicInteger(0);

    private int                        id;
    private String                     title;
    private Boolean                    completed;
    private Integer                    order;
    private String                     url;

    public TodoEntity() {
    }

    public TodoEntity(TodoEntity other) {
        this.id = other.id;
        this.title = other.title;
        this.completed = other.completed;
        this.order = other.order;
        this.url = other.url;
    }

    public TodoEntity(JsonObject obj) {
        TodoEntityConverter.fromJson(obj, this);
    }

    public TodoEntity(String jsonStr) {
        TodoEntityConverter.fromJson(new JsonObject(jsonStr), this);
    }

    public TodoEntity(int id, String title, Boolean completed, Integer order, String url) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
        this.url = url;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        TodoEntityConverter.toJson(this, json);
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIncId() {
        this.id = acc.incrementAndGet();
    }

    public static int getIncId() {
        return acc.get();
    }

    public static void setIncIdWith(int n) {
        acc.set(n);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isCompleted() {
        return getOrElse(completed, false);
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getOrder() {
        return getOrElse(order, 0);
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TodoEntity todo = (TodoEntity) o;

        if (id != todo.id)
            return false;
        if (!title.equals(todo.title))
            return false;
        if (completed != null ? !completed.equals(todo.completed) : todo.completed != null)
            return false;
        return order != null ? order.equals(todo.order) : todo.order == null;

    }

    @Override public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Todo -> {" + "id=" + id + ", title='" + title + '\'' + ", completed=" + completed + ", order=" + order
                + ", url='" + url + '\'' + '}';
    }

    private <T> T getOrElse(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public TodoEntity merge(TodoEntity todo) {
        return new TodoEntity(id, getOrElse(todo.title, title), getOrElse(todo.completed, completed),
                getOrElse(todo.order, order), url);
    }
}
