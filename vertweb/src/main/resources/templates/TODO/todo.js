var backend_uri = "https://todo-backend-sinatra.herokuapp.com/todos",
    counterOrder = 0;

function TodoList() {

    return {
        complete_task: function (task_uid, onFinish) {
            var url = backend_uri + "/" + task_uid;
            $.ajax({
                url: url,
                type: 'PATCH',
                data: JSON.stringify({"completed": true})
            })
            .done(function() {
                onFinish();
            })
            .fail(function(){
                 console.log("Completed Task Failed");
             })
            return true;
        },
        uncomplete_task: function(task_uid, onFinish) {
            var url = backend_uri + "/" + task_uid;
            $.ajax({
                url: url,
                type: 'PATCH',
                data: JSON.stringify({"completed": false})
            })
            .done(function() {
                onFinish();
            })
            .fail(function(){
                 console.log("Uncompleted Task Failed");
             })
            return true;
        },
        edit_task: function(task_uid, changed_title, onFinish) {
            var url = backend_uri + "/" + task_uid;
            $.ajax({
                url: url,
                type: 'PATCH',
                data: JSON.stringify({"title": changed_title})
            })
            .done(function() {
                onFinish();
            })
            .fail(function(){
                 console.log("Edit Task Failed");
             })
            return true;
        },
        add_task: function (task, onFinish) {
            var parsed_task = JSON.stringify(task);

            console.log(parsed_task);
            $.post(backend_uri, parsed_task)
             .done(function() {
                 onFinish();
             })
             .fail(function(){
                 console.log("Add Task Failed");
             })
            return true;
        },
        remove_task: function (task_uid, onFinish) {
            var url = backend_uri + "/" + task_uid;
            $.ajax({
                url: url,
                type: 'DELETE'
            })
            .done(function() {
                onFinish();
            })
            .fail(function(){
                 console.log("Remove Task Failed");
             })
            return true;
        },
        all_tasks: function () {
            var response = $.ajax({
                url: backend_uri,
                async: false,
                type: 'GET'
            });
            var parsed_response = JSON.parse(response.responseText),
                maxOrder = Math.max.apply(Math, parsed_response.map(function(o){
                    return o.order;
                }));

            if (maxOrder > counterOrder) {
                counterOrder = maxOrder + 1;
            }
            
            var sorted_response = parsed_response.sort(function(a, b) {
                    return a.order < b.order; 
                });
            return sorted_response;
        }
    }
}

var todoList = new TodoList();

function add() {
    var title = document.getElementById('task').value,
        counterNext = counterOrder++;
    
    todoList.add_task({'title': title, 'order': counterNext}, showTaskList);
    console.log(title + " " + counterNext);
    return false;
}

$("form").submit(function(e) {
    e.preventDefault();
});

function changeLabel() {
    var id = this.getAttribute('id');

    $('label[id="' + id + '"]').hide();
    $('.edit-input[id="' + id + '"]').show().focus();
    return false;
}

function labelChanged() {
    var id = this.getAttribute('id'),
        changed_title = $('.edit-input[id="' + id + '"]').val();

    todoList.edit_task(id, changed_title, showTaskList);
    return false;
}

function changeStatus(event) {
    var todo = event.data.todo;
    
    if (todo.completed == true) {
        todoList.uncomplete_task(todo.uid, showTaskList)
    } else {
        todoList.complete_task(todo.uid, showTaskList)
    }
}

function isTaskCompleted(todo) {
    return todo.completed == true;  
}

function remove() {
    var id = this.getAttribute('id');

    todoList.remove_task(id, showTaskList);
    return false;
}

var taskList = function() {
    document.getElementById('todos').innerHTML = '';

    for (var todo of todoList.all_tasks()) {
    console.log(todo.uid + " " + todo.completed + " " + todo.order);

        var div =  $("<div>").attr({
            'class': "input-group style"
        });

        var span =  $("<span>").attr({
            'class': "input-group"
        }).appendTo(div);

        var input = $("<input>").attr({
            'type': "checkbox",
            'id': todo.uid,
            'order': todo.order
        })
        .prop({
            'checked': todo.completed
        })
        .click({todo: todo}, changeStatus)
        .appendTo(span);

        var label = $("<label>").attr({
            'for': "checkbox",
            'id': todo.uid,
            'class': "edit"
        })
        .text(todo.title)
        .appendTo(span);
       
       var editInput = $("<input>").attr({
            'class': "edit-input",
            'id': todo.uid
        }).appendTo(span);

        var spanButton =  $("<span>").attr({
            'class': "input-group-btn"
        }).appendTo(div);

        var button = $("<button>").attr({
            'aria-label': "Close",
            'class': "close remove",
            'id': todo.uid
        }).appendTo(spanButton);

        var spanHidden = $("<span>").attr({
            'aria-hidden': "true"
        }).text('X').appendTo(button);

        div.appendTo(todos);
    }

    var buttons = document.getElementsByClassName('remove'),
        edit = document.getElementsByClassName('edit'),
        edit_inputs = document.getElementsByClassName('edit-input');

    for (var i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener('click', remove);
    };
  
    for (var i = 0; i < edit.length; i++) {
        edit[i].addEventListener('dblclick', changeLabel);
        edit[i].addEventListener('touchmove', changeLabel);
    };

    for (var i = 0; i < edit_inputs.length; i++) {
        edit_inputs[i].addEventListener('focusout', labelChanged);
    };
}   

// CALCULATE COUNTER
function calculateCounter() {
    var $counter = $('#counter'),
        $inputs = $("input[type=checkbox]"),
        $inputsCh = $inputs.filter(':checked'),
        tempArray = [$inputsCh.length, $inputs.length],
        informationText = tempArray[1]-tempArray[0];
    $counter.html(informationText);
}

// FILTERS
var activeFilter = "all";

var filterByAll = function () {
    activeFilter = "all";
    var inputs = $("input[type=checkbox]");
    $('#allFilter').addClass("active");
    $('#completedFilter').removeClass("active");
    $('#activeFilter').removeClass("active");
    return inputs.parents().show();
}
document.querySelector("#allFilter").addEventListener('click', filterByAll, false);

var filterByActive = function() {
    activeFilter = "active";
    var $inputs = $("div input[type=checkbox]"),
        $inputsCh = $inputs.filter(":checked"),
        $inputsNotCh = $inputs.filter(":not(:checked)"),
        $parentInputs = $inputsCh.parent().parent();
    $('#activeFilter').addClass("active");
    $('#allFilter').removeClass("active");
    $('#completedFilter').removeClass("active");
    return ($parentInputs.hide(), $inputsNotCh.parents().show());
    showTaskList();
}
document.querySelector("#activeFilter").addEventListener('click', filterByActive, false);

var filterByCompleted = function() {
    activeFilter = "completed";
    var $inputs = $("div input[type=checkbox]"),
        $inputsCh = $inputs.filter(":checked"),
        $inputsNotCh = $inputs.filter(":not(:checked)"),
        $parentInputs = $inputsNotCh.parent().parent();
    $('#completedFilter').addClass("active");
    $('#allFilter').removeClass("active");
    $('#activeFilter').removeClass("active");
    return ($parentInputs.hide(), $inputsCh.parents().show());  
    showTaskList();
}
document.querySelector("#completedFilter").addEventListener('click', filterByCompleted, false);

var filterTasksBy = function(filter) {
    if(activeFilter == "all") {
        filterByAll();
    } else if(activeFilter == "active") {
        filterByActive();        
    } else {
        filterByCompleted();
    }
}

function showTaskList() {
    taskList();
    filterTasksBy(activeFilter);
    calculateCounter();
}

document.getElementById('add').addEventListener('click', add);
showTaskList();

// SELECT BUTTONS
function selectAll() {
    var inputs = $("input[type=checkbox]");

    for (var i = 0; i < inputs.length; i++ ) {
        inputs[i].checked=true;
        todoList.complete_task(inputs[i].id, showTaskList); 
    }
}
document.querySelector("#selectAll").addEventListener('click', selectAll, false);

function deselectAll() {
    var inputs = $("input[type=checkbox]");

    for (var i = 0; i < inputs.length; i++ ) {
        inputs[i].checked=false;
        todoList.uncomplete_task(inputs[i].id, showTaskList);
    }
}
document.querySelector("#deselectAll").addEventListener('click', deselectAll, false);

// CLEAR COMPLITED
function completedRemove() {
    var inputs = $("input[type=checkbox]");

    for (var i = 0; i < inputs.length; i++ ) {
        if (inputs[i].checked == true) {
            todoList.remove_task(inputs[i].id, showTaskList)
        }
    }        
}
document.querySelector("#completedRemove").addEventListener('click', completedRemove, false);