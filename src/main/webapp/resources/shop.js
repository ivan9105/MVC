var cx;
var cy;
var categoryRect = null;
var showPopup = false;
var ddTreeMenu = {};
var selectedCategory = null;
var tableSize = 15;
var host = "http://localhost:8080";

document.onmousemove = function (e) {
    cx = e.pageX;
    cy = e.pageY;
};

window.onscroll = function (e) {
    var scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    var sidebar = document.getElementById('sidebar');
    if (sidebar != 'null' && sidebar != null && sidebar != 'undefined') {
        sidebar.style.top = 95 + scrollTop + 'px';
        var popup = document.getElementById('popupMenu');
        if (popup != 'null' && popup != null && popup != 'undefined') {
            popup.style.top = 100 + scrollTop + 'px';
        }
    }
};

function StringBuilder(value) {
    this.strings = [];
    this.append(value);
}

StringBuilder.prototype.append = function (value) {
    if (value) {
        this.strings.push(value);
    }
};

StringBuilder.prototype.clear = function () {
    this.strings.length = 0;
};

StringBuilder.prototype.toString = function () {
    return this.strings.join("");
};

var CategoryClass = function (id, name, description, level, parentId, child_) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.level = level;
    this.parentId = parentId;
    this.child_ = child_;
};

CategoryClass.fromJson = function (json) {
    var obj = JSON.parse(json);
    return new CategoryClass(obj.id, obj.name, obj.description, obj.level, obj.parentId, obj.child_);
};

CategoryClass.fromObject = function (obj) {
    return new CategoryClass(obj.id, obj.name, obj.description, obj.level, obj.parentId, obj.child_);
};

CategoryClass.toString = function () {
    return "Id: " + this.id + ",\nName: " + this.name + ",\nDescription: " + this.description +
        ",\nLevel: " + this.level + ",\nParentId: " + this.parentId;
};

var ItemClass = function (id, name, description, count, price, categoryId, categoryName) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.count = count;
    this.price = price;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
};

ItemClass.fromJson = function (json) {
    var obj = JSON.parse(json);
    return new ItemClass(obj.id, obj.name, obj.description, obj.count, obj.price, obj.categoryId, obj.categoryName)
};

ItemClass.fromObject = function (obj) {
    return new ItemClass(obj.id, obj.name, obj.description, obj.count, obj.price, obj.categoryId, obj.categoryName)
};

ItemClass.toString = function () {
    return "Id: " + this.id + ",\nName: " + this.name + ",\nDescription: " + this.description +
        ",\nCount: " + this.count + ",\nPrice: " + this.price + ",\nCategoryId: " + this.categoryId +
        ",\nCategoryName: " + this.categoryName;
};

function removePopup(popupMenu) {
    popupMenu.remove();
    $("div#categoryMenu").children().attr("class", "list-group-item");
    showPopup = false;
}

function popupMOut(e) {
    var popupMenu = document.getElementById("popupMenu");
    if (e != 'null' && e != 'undefined') {
        var target = e.relatedTarget;
        while (target.parentNode != null && target != popupMenu) {
            target = target.parentNode;
        }
        if (target != popupMenu && target.parentNode != popupMenu) {
            removePopup(popupMenu);
        }
    } else {
        removePopup(popupMenu);
    }
}

function popupMOver() {
}

function categoryMOut() {
}

var createSubMenu = function createSubMenu(result, div) {
    var sb = new StringBuilder();
    sb.append('<div id="menuDiv" style="margin-left: -40px; margin-top:13px;">');
    sb.append('<ul id="treeMenu_" class="treeview">');

    for (var i = 0; i < result.data.length; i++) {
        var obj = result.data[i];
        //Todo create links with logic
        if (obj.child == 'null' || obj.child == 'undefined' || obj.child.length == 0) {
            sb.append('<li class="treeCaptionRoot" rel="root">' + obj.name + '<b class="countItems">' + obj.itemsCount + '</b></li>');
        } else {
            sb.append('<li rel="root"><p class="treeCaptionRoot withoutSpace"><i class="fa fa-caret-down" style="margin-right: 3px;"/>' + obj.name + '<b class="countItems">' + obj.itemsCount + '</b>' + '</p>');
            fillSubMenuList(obj.child, sb);
            sb.append('</li>')
        }
    }

    sb.append('</ul>');
    sb.append('</div>');

    var treeMenu = $(sb.toString());
    div.append(treeMenu);
    ddTreeMenu.createTree("treeMenu_", true, 600);
    ddTreeMenu.flatten("treeMenu_", "expand");
};

function fillSubMenuList(result, sb) {
    sb.append('<ul>');
    for (var i = 0; i < result.length; i++) {
        var obj = result[i];
        if (obj.child == 'null' || obj.child == 'undefined' || obj.child.length == 0) {
            sb.append('<li>' + obj.name + '<b class="countItems">' + obj.itemsCount + '</b></li>');
        } else {
            sb.append('<li><i class="fa fa-caret-down" style="margin-right: 3px; "/>' + obj.name + '<b class="countItems">' + obj.itemsCount + '</b>');
            fillSubMenuList(obj.child, sb);
            sb.append('</li>')
        }
    }
    sb.append('</ul>');
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
        var div = $('<div id="popupMenu" onmouseover="popupMOver()" onmouseout="popupMOut(event)" class="container">').css({
            "position": "absolute",
            "left": x + "px",
            "top": y + "px",
            "width": "30%",
            "height": "70%",
            "background-color": "white",
            "border": "1px solid #DDDDDD"
        });

        $(document.body).append(div);
        showPopup = true;

        getChildCategories(host, href.getAttribute("rel"), div, createSubMenu);
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

function getRootCategories(host, callback) {
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", host + "/api/shop/rootCategories", true);
    xmlHttpRequest.onload = function () {
        var result = [];
        var json = xmlHttpRequest.responseText;
        var obj = JSON.parse(json);
        var length = obj['data'].length;
        for (var i = 0; i < length; i++) {
            result.push(CategoryClass.fromObject(obj['data'][i]));
        }
        callback(result);
    };
    xmlHttpRequest.send(null);
}

function getChildCategories(host, id, div, callback) {
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", host + "/api/shop/childCategories?id=" + id, true);
    xmlHttpRequest.onload = function () {
        var result = [];
        var json = xmlHttpRequest.responseText;
        var obj = JSON.parse(json);
        callback(obj, div);
    };
    xmlHttpRequest.send(null);
}

var initMenu = function initRootMenu(categories) {
    var categoryMenu = document.getElementById('categoryMenu');
    if (categoryMenu != 'null') {
        for (var i = 0; i < categories.length; i++) {
            var category = categories[i];
            if (category.level == '0') {
                console.log(category);
                $("div#categoryMenu").append('<a href="#" onmouseover="categoryMOver(this)" onmouseout="categoryMOut()" ' +
                    'class="list-group-item" rel="' + category.id + '">' + category.name + '</a>');
            }
        }
    }
    categoryRect = getRect(categoryMenu)
};

function logMousePosition(message) {
    if (message != 'null') {
        console.log(message + " x: " + cx + ", y: " + cy);
    } else {
        console.log("Mouse out x: " + cx + ", y: " + cy);
    }
}

function getItems(host, page, callback) {
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", host + "/api/shop/" + (selectedCategory == null ? "items" : "categoryItems")
        + "?size=" + tableSize
        + (page != null ? "&page=" + page : "")
        + (selectedCategory != null ? "&categoryId=" + selectedCategory : "")
        , true);
    xmlHttpRequest.onload = function () {
        var result = [];
        var json = xmlHttpRequest.responseText;
        var obj = JSON.parse(json);
        var length = obj['data'].length;
        for (var i = 0; i < length; i++) {
            result.push(ItemClass.fromObject(obj['data'][i]));
        }
        callback(result, obj['currentPage'], obj['pageSize']);
    };
    xmlHttpRequest.send(null);
}

var initTable = function initTable(items, currentPage, pageSize) {
    var itemsTable = document.getElementById('itemsTable');
    var tbody = itemsTable.getElementsByTagName('TBODY')[0];
    var tablePagination = document.getElementById('tablePagination');

    var rows = tbody.getElementsByTagName('TR');
    var length = rows.length - 1;
    while (length >= 0) {
        tbody.removeChild(rows[length]);
        length--;
    }

    if (tbody != null && tbody != 'null' && tbody != 'undefined') {
        for (var i = 0; i < items.length; i++) {
            var row = tbody.insertRow(0);
            var nameCell = row.insertCell(0);
            var priceCell = row.insertCell(1);
            var categoryCell = row.insertCell(2);
            var countCell = row.insertCell(3);

            nameCell.appendChild(document.createTextNode(items[i].name));
            priceCell.appendChild(document.createTextNode(items[i].price));
            categoryCell.appendChild(document.createTextNode(items[i].categoryName));
            countCell.appendChild(document.createTextNode(items[i].count));
        }
    }

    if (tablePagination != null && tablePagination != 'null' && tablePagination != 'undefined') {
        var sb = new StringBuilder();
        sb.append('<ul class="pagination">');
        if (currentPage > 5) {
            sb.append('<li><a href="#" onclick="fillTable(' + 1 + ')">&laquo;</a></li>');
            sb.append('<li><a href="#" onclick="fillTable(' + (currentPage - 1) + ')">&larr;</a></li>');
        }

        var min;
        var max;
        if (currentPage <= 5) {
            min = 1;
            max = 5;
        } else {
            var minCount = currentPage;
            var maxCount = currentPage;
            while (minCount % 5 != 0) {
                minCount--;
            }
            while (maxCount % 5 != 0) {
                maxCount++;
            }

            min = minCount + 1;
            max = maxCount;
        }
        if (max <= min) {
            min = max - 4;
        }
        if (max > pageSize) {
            max = pageSize;
        }

        for (var j = min; j <= max; j++) {
            if (j == currentPage) {
                sb.append('<li><a href="#" class="active" onclick="fillTable(' + j + ')">' + j + '</a></li>');
            } else {
                sb.append('<li><a href="#" onclick="fillTable(' + j + ')">' + j + '</a></li>');
            }
        }

        if (pageSize > 5 && pageSize >= min + 5) {
            sb.append('<li><a href="#" onclick="fillTable(' + (currentPage + 1) + ')">&rarr;</a></li>');
            sb.append('<li><a href="#" onclick="fillTable(' + pageSize + ')">&raquo;</a></li>');
        }
        sb.append('</ul>');
        tablePagination.innerHTML = sb.toString();
    }
};

function fillMenu() {
    getRootCategories(host, initMenu);
    initTreeMenuBuilder();
}

function fillTable(page) {
    getItems(host, page, initTable);
}

function onMouseMenuWrapper(e) {
    var x = e.clientX;
    var y = e.clientY;

    if (showPopup && categoryRect != 'null') {
        if (x < categoryRect[0] || y < categoryRect[1] ||
            (x >= categoryRect[0] && x <= categoryRect[2] && y > categoryRect[3])) {
            var popupMenu = $("div#popupMenu");
            removePopup(popupMenu);
        }
    }
}

function initTreeMenuBuilder() {
    var arr = {};

    ddTreeMenu.createTree = function (treeId, enablePersist, persistDays) {
        var treeElement = document.getElementById(treeId);
        if (treeElement != 'null' && treeElement != 'undefined' && treeElement != null) {
            var ulTags = treeElement.getElementsByTagName("ul");
            if (typeof arr[treeId] == "undefined") {
                arr[treeId] = (enablePersist == true && ddTreeMenu.getCookie(treeId) != "") ? ddTreeMenu.getCookie(treeId).split(",") : "";
            }

            for (var i = 0; i < ulTags.length; i++) {
                ddTreeMenu.buildSubTree(treeId, ulTags[i], i);
            }

            if (enablePersist == true) {
                var days = (typeof persistDays == "undefined") ? 1 : parseInt(persistDays);
                ddTreeMenu.doTask(window, function () {
                    ddTreeMenu.rememberState(treeId, days);
                }, "unload");
            }
        }
    };

    ddTreeMenu.buildSubTree = function (treeId, ulElement, index) {
        ulElement.parentNode.className = "submenu";
        if (typeof arr[treeId] == "object") { //if cookie exists
            if (ddTreeMenu.contains(arr[treeId], index)) {
                ulElement.setAttribute("rel", "open");
                ulElement.style.display = "block";
                ddTreeMenu.switchState(ulElement.parentNode, true);
            } else {
                ulElement.setAttribute("rel", "closed");
            }
        } else if (ulElement.getAttribute("rel") == null || ulElement.getAttribute("rel") == false) {
            ulElement.setAttribute("rel", "closed");
        } else if (ulElement.getAttribute("rel") == "open") {
            ddTreeMenu.expandSubTree(treeId, ulElement)
        }

        ulElement.parentNode.onclick = function (event) {
            var submenu = this.getElementsByTagName("UL")[0];
            if (submenu.getAttribute("rel") == "closed") {
                submenu.style.display = "block";
                submenu.setAttribute("rel", "open");
                ddTreeMenu.switchState(ulElement.parentNode, true);
            } else if (submenu.getAttribute("rel") == "open") {
                submenu.style.display = "none";
                submenu.setAttribute("rel", "closed");
                ddTreeMenu.switchState(ulElement.parentNode, false);
            }
            ddTreeMenu.preventPropagate(event);
        };

        ulElement.onclick = function (event) {
            ddTreeMenu.preventPropagate(event);
        }
    };

    ddTreeMenu.expandSubTree = function (treeId, ulElement) {
        var rootNode = document.getElementById(treeId);
        var currentNode = ulElement;
        currentNode.style.display = "block";
        ddTreeMenu.switchState(currentNode.parentNode, true);
        while (currentNode != rootNode) {
            if (currentNode.tagName == "UL") {
                currentNode.style.display = "block";
                currentNode.setAttribute("rel", "open");
                ddTreeMenu.switchState(currentNode.parentNode, true);
            }
            currentNode = currentNode.parentNode;
        }
    };

    ddTreeMenu.flatten = function (treeId, action) {
        var treeElement = document.getElementById(treeId);
        if (treeElement != null && treeElement != 'null' && treeElement != 'undefined') {
            var ulTags = treeElement.getElementsByTagName("UL");
            for (var i = 0; i < ulTags.length; i++) {
                ulTags[i].style.display = (action == "expand") ? "block" : "none";
                var relValue = (action == "expand") ? "open" : "closed";
                ulTags[i].setAttribute("rel", relValue);
                if (action == "expand") {
                    ddTreeMenu.switchState(ulTags[i].parentNode, true);
                } else {
                    ddTreeMenu.switchState(ulTags[i].parentNode, false);
                }
            }
        }
    };

    ddTreeMenu.rememberState = function (treeId, days) {
        var ulTags = document.getElementById(treeId).getElementsByTagName("UL");
        var opened = [];
        for (var i = 0; i < ulTags.length; i++) {
            if (ulTags[i].getAttribute("rel") == "open") {
                opened[opened.length] = i;
            }
        }

        if (opened.length == 0) {
            opened[0] = "none open";
        }

        ddTreeMenu.setCookie(treeId, opened.join(","), days);
    };

    ddTreeMenu.getCookie = function (name) {
        var regExp = new RegExp(name + "=[^;]+", "i");
        if (document.cookie.match(regExp)) {
            return document.cookie.match(regExp)[0].split("=")[1];
        }
        return "";
    };

    ddTreeMenu.setCookie = function (name, value, days) {
        var expirationDate = new Date();
        expirationDate.setDate(expirationDate.getDate() + parseInt(days));
        document.cookie = name + "=" + value + "; expires=" + expirationDate.toGMTString() + "; path=/";
    };

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
    };

    ddTreeMenu.preventPropagate = function (event) {
        if (typeof event != "undefined") {
            event.stopPropagation();
        } else {
            event.cancelBubble = true;
        }
    };

    ddTreeMenu.doTask = function (target, functionRef, taskType) {
        var taskType_ = (window.addEventListener) ? taskType : "on" + taskType;
        if (target.addEventListener) {
            target.addEventListener(taskType_, functionRef, false);
        } else if (target.attachEvent) {
            target.attachEvent(taskType_, functionRef);
        }
    };

    ddTreeMenu.switchState = function (element, open) {
        var rel = element.getAttribute("rel");
        var icon = null;
        var paragraph = null;

        var elementChild = element.childNodes;
        for (var i = 0; i < elementChild.length; i++) {
            if (elementChild[i].tagName == 'I') {
                icon = elementChild[i];
                break;
            } else if (elementChild[i].tagName == 'P') {
                paragraph = elementChild[i];
            }
        }

        if (icon == null && paragraph != null) {
            var paragraphChild = paragraph.childNodes;
            for (var j = 0; j < paragraphChild.length; j++) {
                if (paragraphChild[j].tagName == 'I') {
                    icon = paragraphChild[j];
                    break;
                }
            }
        }

        if (open) {
            if (element.className.indexOf("closed underline") != -1) {
                element.className = element.className.replace(/closed underline/g, "open");
            } else if (element.className.indexOf("closed") != -1) {
                element.className = element.className.replace(/closed/g, "open");
            } else if (element.className.length == 0) {
                element.className += "open";
            } else {
                element.className = "open";
            }
            icon.className = "fa fa-caret-down";
        } else {
            if (element.className.indexOf("open") != -1) {
                element.className = element.className.replace(/open/g, (rel == 'root' ? "closed underline" : "closed"));
            } else if (element.className.length == 0) {
                element.className += (rel == 'root' ? "closed underline" : "closed");
            } else {
                element.className = (rel == 'root' ? "closed underline" : "closed");
            }
            icon.className = "fa fa-caret-up";
        }
    }
}