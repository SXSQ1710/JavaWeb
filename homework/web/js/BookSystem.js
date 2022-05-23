function back(){
    window.location.href='BookSystem?operate=index';
}

function add() {
    window.location.href='BookSystem?operate=add';
}

function deleBook(isbn){
    if(confirm('是否确认删除？')){
        window.location.href='BookSystem?isbn='+isbn+'&operate=deletBook';
    }
}

function aletBook(isbn) {
    window.location.href='BookSystem?isbn='+isbn+'&operate=alet';
}

