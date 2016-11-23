/**
 * Created by caly on 10/19/2016.
 */

function listStorys(storys) {
    var myStoryHTML = document.getElementById('myStoryHTML')
    var listHtml = '';

    for (var i = 0; i < storys.length; i++) {
        var story = storys[i];
        //var checked = task.done ? ' checked=""' : '';
        var storyHtml =
            '<p>' +
            story.insertDate +
            '<br> ' +
            story.story +
            '</p>';
        listHtml += storyHtml;
    }
    myStoryHTML.innerHTML = listHtml;
}

function markDone(id) {

    $.ajax({
        url: 'removetask?id='+id
    }).done(function (response) {
        loadTasks();
    });
}

function loadStorys() {
    $.ajax({
        url: 'mystory'
    }).done(function (response) {
        listStorys(response.myStory);
    });
}

function addStory() {
    var toDoText = document.getElementById('storylist').value;
    $.ajax({
        url: 'addstory?textToStory='+toDoText
    }).done(function (response) {
        location.href = "diary.html";
    });
}
