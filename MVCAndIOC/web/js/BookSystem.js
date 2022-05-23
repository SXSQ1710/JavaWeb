function back(){
    window.location.href='BookSystem.do?operate=index';
}

function add() {
    window.location.href='BookSystem.do?operate=add';
}

function deleBook(isbn){
    if(confirm('是否确认删除？')){
        window.location.href='BookSystem.do?isbn='+isbn+'&operate=deletBook';
    }
}

function aletBook(isbn) {
    window.location.href='BookSystem.do?isbn='+isbn+'&operate=alet';
}

