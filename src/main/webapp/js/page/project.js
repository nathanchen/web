function list_item_highlight() {
    var path = document.location.pathname;
    $("div.list-group a").each(function(){
        var spath = $(this).attr("href");
        if(typeof(spath) == "string" && spath.indexOf(path) >= 0){
            $(this).addClass("active");
            return false;
        }
    });
}

list_item_highlight();