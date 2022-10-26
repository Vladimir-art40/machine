function send(qwe) {
    document.getElementById('black').classList.add('black-pane');
    const request = new XMLHttpRequest();
    request.open("GET", qwe, true);
    request.send(null );
    request.onreadystatechange = function() {
        document.getElementById('black').classList.remove('black-pane');
    }
}