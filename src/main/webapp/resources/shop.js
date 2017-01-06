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
    for (i = 0; i < categories.length; i++) {
        console.log(categories[i]);
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

//getItems("", "05d8a44b-e144-4f78-9fd4-c4b73a57379b", initTable);
//getCategories("http://localhost:8080", initMenu);