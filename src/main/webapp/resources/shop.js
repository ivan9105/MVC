var cx;
var cy;
var categoryRect = null;
var showPopup = false;

document.onmousemove = function (e) {
    cx = e.pageX;
    cy = e.pageY;
}

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

function popupMOut() {
    $("div#popupMenu").remove();
    $("div#categoryMenu").children().attr("class", "list-group-item");
    showPopup = false;
}

function popupMOver() {
}

function categoryMOut() {
}

function categoryMOver(href) {
    $("div#categoryMenu").children().attr("class", "list-group-item");
    href.className = "list-group-item active";
    var categoryMenu = document.getElementById('categoryMenu');
    if (categoryMenu != 'null') {
        $("div#popupMenu").remove();
        var coords = getCoords(categoryMenu);
        var x = coords[1][0];
        var y = coords[1][1];
        var div = $('<div id="popupMenu" onmouseover="popupMOver()" onmouseout="popupMOut()" class="container">').css({
            "position": "absolute",
            "left": x + "px",
            "top": y + "px",
            "width": "30%",
            "height": "70%",
            "background-color": "white",
            "border": "1px solid black"
        });
        $(document.body).append(div);
        showPopup = true;
    }
}

function getCoords(element) {
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

    res.push([Math.round(left), Math.round(top)]);
    res.push([Math.round(left) + Math.round(rect.width), Math.round(top)]);
    res.push([Math.round(left), Math.round(top) + Math.round(rect.height)]);
    res.push([Math.round(left) + Math.round(rect.width), Math.round(top) + Math.round(rect.height)]);
    return res;
}

function getRect(element) {
    var res = [];
    var coords = getCoords(element);
    res.push(coords[0][0]);//x1
    res.push(coords[0][1]);//y1
    res.push(coords[3][0]);//x2
    res.push(coords[3][1]);///y2
    return res;
}

function cursorInRect(element) {
    var r = getRect(element);
    return cx >= r[0] && cx <= r[2] && cy >= r[1] && cy <= r[3];
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
    if (categoryMenu != 'null') {
        for (i = 0; i < categories.length; i++) {
            var category = categories[i];
            if (category.level == '0') {
                console.log(category);
                $("div#categoryMenu").append('<a href="#" onmouseover="categoryMOver(this)" onmouseout="categoryMOut()" class="list-group-item">' + category.name + '</a>');
            }
        }
    }
    categoryRect = getRect(categoryMenu)
}

function logMousePosition(message) {
    if (message != 'null') {
        console.log(message + " x: " + cx + ", y: " + cy);
    } else {
        console.log("Mouse out x: " + cx + ", y: " + cy);
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

function onMouseMenuWrapper(e) {
    var x = e.clientX;
    var y = e.clientY;

    if (showPopup && categoryRect != 'null') {
        if (x < categoryRect[0] || y < categoryRect[1] ||
            (x >= categoryRect[0] && x <= categoryRect[2] && y > categoryRect[3])) {
            popupMOut();
        }
    }
}

function initTreeMenu(parent) {
    var arr = {};
    var ddTreeMenu = {};

    ddTreeMenu.closefolder = "img/closed.gif";
    ddTreeMenu.openfolder = "img/open.gif";

    ddTreeMenu.createTree = function (treeId, enablePersist, persistDays) {
        var ulTags = document.getElementById(treeId).getElementsByTagName("ul");
        if (typeof arr[treeId] == "undefined") {
            arr[treeId] = (enablePersist == true && ddTreeMenu.getCookie(treeId) != "") ? ddTreeMenu.getCookie(treeId).split(",") : "";
        }

        for (var i = 0; i < ulTags.length; i++) {
            //Todo
        }
        //Todo
    }

    ddTreeMenu.buildSubTree = function (treeId, ulElement, index) {
        ulElement.parentNode.className = "submenu";
        if (typeof arr[treeId] == "object") { //if cookie exists
            if (ddTreeMenu.contains(arr[treeId], index)) {
                ulElement.setAttribute("rel", "open");
                ulElement.style.display = "block";
                ulElement.parentNode.style.backgroundImage = "url(" + ddTreeMenu.openfolder + ")";
            } else {
                ulElement.setAttribute("rel", "closed");
            }
        } else if (ulElement.getAttribute("rel") == null || ulElement.getAttribute("rel") == false) {
            ulElement.setAttribute("rel", "closed");
        } else if (ulElement.getAttribute("rel") == "open") {
            ddTreeMenu.expandSubTree(treeId, ulElement)
        }

        ulElement.parentNode.onclick = function (event) {
            var submenu = this.getElementsByTagName("UL")[0]; //Todo check
            if (submenu.getAttribute("rel") == "closed") {
                submenu.style.display = "block";
                submenu.setAttribute("rel", "open");
                ulElement.parentNode.style.backgroundImage = "url(" + ddTreeMenu.openfolder + ")";
            } else if (submenu.getAttribute("rel") == "open") {
                submenu.style.display = "none";
                submenu.setAttribute("rel", "closed");
                ulElement.parentNode.style.backgroundImage = "url(" + ddTreeMenu.closefolder + ")";
            }
            ddTreeMenu.preventPropagate(event);
        }

        ulElement.onclick = function (event) {
            ddTreeMenu.preventPropagate(event);
        }
    }

    ddTreeMenu.expandSubTree = function (treeId, ulElement) {
        var rootNode = document.getElementById(treeId);
        var currentNode = ulElement;
        currentNode.style.display = "block";
        currentNode.parentNode.style.backgroundImage = "url(" + ddTreeMenu.openfolder + ")";
        while (currentNode != rootNode) {
            if (currentNode.tagName == "UL") {
                currentNode.style.display = "block";
                currentNode.setAttribute("rel", "open");
                currentNode.parentNode.style.backgroundImage = "url(" + ddTreeMenu.openfolder + ")";
            }
            currentNode = currentNode.parentNode;
        }
    }

    ddTreeMenu.getCookie = function (name) {
        var regExp = new RegExp(name + "=[^;]+", "i");
        if (document.cookie.match(regExp)) {
            return document.cookie.match(regExp)[0].split("=")[1];
        }
        return "";
    }

    ddTreeMenu.setCookie = function (name, value, days) {
        var expirationDate = new Date();
        expirationDate.setDate(expirationDate.getDate() + parseInt(days));
        document.cookie = name + "=" + value + "; expires=" + expirationDate.toGMTString() + "; path=/";
    }

    ddTreeMenu.contains = function (arr, value) {
        var found = false;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                found = true;
                arr.shift();
                break;
            }
        }
        return found;
    }

    ddTreeMenu.preventPropagate = function (event) {
        if (typeof event != "undefined") {
            event.stopPropagation();
        } else {
            event.cancelBubble = true;
        }
    }

    ddTreeMenu.doTask = function (target, functionRef, taskType) {
        var taskType_ = (window.addEventListener) ? taskType : "on" + taskType;
        if (target.addEventListener) {
            target.addEventListener(taskType_, functionRef, false);
        } else if (target.attachEvent) {
            target.attachEvent(taskType_, functionRef);
        }
    }
}

//getItems("", "05d8a44b-e144-4f78-9fd4-c4b73a57379b", initTable);
//getCategories("http://localhost:8080", initMenu);