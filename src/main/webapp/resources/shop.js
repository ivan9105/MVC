var CategoryClass = function (id, name, description, level, parentId, child_) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.level = level;
    this.parentId = parentId;
    this.child_ = child_;
}

CategoryClass.fromJson = function (json) {
    var obj = JSON.parse(json);
    return new CategoryClass(obj.id, obj.name, obj.description, obj.level, obj.parentId, obj.child_);
}

CategoryClass.fromObject = function (obj) {
    return new CategoryClass(obj.id, obj.name, obj.description, obj.level, obj.parentId, obj.child_);
}

CategoryClass.toString = function () {
    return "Id: " + this.id + ",\nName: " + this.name + ",\nDescription: " + this.description +
        ",\nLevel: " + this.level + ",\nParentId: " + this.parentId;
}

var ItemClass = function (id, name, description, count, price, categoryId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.count = count;
    this.price = price;
    this.categoryId = categoryId;
}

ItemClass.fromJson = function (json) {
    var obj = JSON.parse(json);
    return new ItemClass(obj.id, obj.name, obj.description, obj.count, obj.price, obj.categoryId)
}

ItemClass.fromObject = function (obj) {
    return new ItemClass(obj.id, obj.name, obj.description, obj.count, obj.price, obj.categoryId)
}

ItemClass.toString = function () {
    return "Id: " + this.id + ",\nName: " + this.name + ",\nDescription: " + this.description +
        ",\nCount: " + this.count + ",\nPrice: " + this.price + ",\nCategoryId: " + this.categoryId;
}

function categoryMOver(href) {
    $("div#categoryMenu").children().attr("class", "list-group-item");
    href.className = "list-group-item active";
    var categoryMenu = document.getElementById('categoryMenu');
    if (categoryMenu != 'null') {
        var coords = getPopupMenuCoords(categoryMenu);
        var x = coords[0];
        var y = coords[1];
        console.log("x: " + x + ", y: " + y);
        //Todo context menu sample for create tree menu div
    }
}

function getPopupMenuCoords(element) {
    var res = [];

    var rect = element.getBoundingClientRect();
    var body = document.body;
    var documentElement = document.documentElement;

    var scrollTop = window.pageYOffset || documentElement.scrollTop || body.scrollTop;
    var scrollLeft = window.pageXOffset || documentElement.scrollLeft || body.scrollLeft;
    var clientTop = documentElement.clientTop || body.clientTop || 0;
    var clientLeft = documentElement.clientLeft || body.clientLeft || 0;

    var top = rect.top + scrollTop - clientTop;
    var left = rect.left + scrollLeft - clientLeft;

    res.push(Math.round(top));
    res.push(Math.round(left) + Math.round(rect.width));
    return res;
}

function categoryMOut() {
    $("div#categoryMenu").children().attr("class", "list-group-item");
}

function getCategories(host, callback) {
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", host + "/api/shop/categories", true);
    xmlHttpRequest.onload = function () {
        var result = [];
        var json = xmlHttpRequest.responseText;
        var obj = JSON.parse(json);
        var length = obj['data'].length;
        for (i = 0; i < length; i++) {
            result.push(CategoryClass.fromObject(obj['data'][i]));
        }
        callback(result);
    }
    xmlHttpRequest.send(null);
}

var initMenu = function initTreeMenu(categories) {
    var categoryMenu = document.getElementById('categoryMenu');
    var first = false;
    if (categoryMenu != 'null') {
        for (i = 0; i < categories.length; i++) {
            var category = categories[i];
            if (category.level == '0') {
                console.log(category);
                if (!first) {
                    $("div#categoryMenu").append('<a href="#" onmouseover="categoryMOver(this)" onmouseout="categoryMOut()" class="list-group-item active">' + category.name + '</a>');
                    first = true;
                } else {
                    $("div#categoryMenu").append('<a href="#" onmouseover="categoryMOver(this)" onmouseout="categoryMOut()" class="list-group-item">' + category.name + '</a>');
                }
            }
        }
    }
}

function getItems(host, categoryId, callback) {
    var xmlHttpRequest = new XMLHttpRequest();
    if (categoryId != 'null') {
        xmlHttpRequest.open("GET", host + "/api/shop/categories", true);
    } else {
        xmlHttpRequest.open("GET", host + "/api/shop/categoryItems?categoryId=" + categoryId, true);
    }
    xmlHttpRequest.onload = function () {
        var result = [];
        var json = xmlHttpRequest.responseText;
        var obj = JSON.parse(json);
        var length = obj['data'].length;
        for (i = 0; i < length; i++) {
            result.push(ItemClass.fromObject(obj['data'][i]));
        }
        callback(result);
    }
    xmlHttpRequest.send(null);
}

var initTable = function initTable(items) {
    for (i = 0; i < items.length; i++) {
        console.log(items[i]);
    }
}

function fillMenu() {
    getCategories("http://localhost:8080", initMenu);
}
//getItems("", "05d8a44b-e144-4f78-9fd4-c4b73a57379b", initTable);
//getCategories("http://localhost:8080", initMenu);