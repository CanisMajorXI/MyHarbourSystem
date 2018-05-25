
$(function () {

    /**
     * 这个方法主要用来获得Json数据
     * @param url 目标API地址
     * @param data 传入的参数
     * @param func 传入的函数
     * @returns {{type: string, dataType: string, url: *, data: *, success: success, error: error}}
     */
    function getContainerInformation(url, data, func) {
        return {
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            success: function (result) {
                func(result);
            },
            error: function () {
                alert("error!");
            }
        };
    }

    /**
     * 已经弃用的方法
     * 表格行列颜色改变函数
     *
     function altRows() {
                var rows = document.getElementsByClassName("my-tr");

                for (i = 0; i < rows.length; i++) {
                    if (i % 2 == 0) {
                        rows[i].className = "evenrowcolor";
                    } else {
                        rows[i].className = "oddrowcolor";
                    }
                }
            }
     */

    /**
     * jQuery重写的表格颜色变换函数
     */
    function altRowsjq() {
        let rows = $(".my-tr");

        for (let i = 0; i < rows.length; i++) {
            if (i % 4 == 0) {
                $(rows[i]).addClass("evenrowcolor");
            } else if (i % 4 == 2) {
                $(rows[i]).addClass("oddrowcolor");
            }
        }
    }



    /**---------------初始化页面数据------------------ **/
    let containerDataJson;
    let cargoDataJson;

    let containerPageNum = 1;
    let containerPageMaxNum = 1;
    let currentContainerPageMaxNum = 5;
    let currentContainerPageMinxNum = 1;

    let cargoPageNum = 1;
    let cargoPageMaxNum = 1;
    let currentCargoPageMaxNum = 5;
    let currentCargoPageMinxNum = 1;

    $("#container-pagination").hide();
    $("#cargo-pagination").hide();
    const containerTable = $("#container-table-tbody");
    const cargoTable = $("#cargo-table-tbody");

    /**---------------初始化页面数据------------------ **/


    /**
     * 用来分页展示Json数据到箱子的表格
     * @param content 是处理后的Json数据
     * @param pageNum 是对应的页数
     * @param table 表格父节点
     */
    function getDataByPage(content, pageNum, table) {
        let type = {
            0: "普通箱",
            1: "冷冻箱",
            2: "危险箱"
        };
        let size = {
            0: "小箱子",
            1: "大箱子"
        };

        // 获得分页数据
        pageNum = parseInt(pageNum);
        pageNum--;

        // 先清空表格内容
        table.empty();

        // 这里截取获得需要页面的Json数据
        let temContent = content.slice(pageNum * 9, pageNum * 9 + 9);

        // 遍历添加内容
        $.each(temContent, function (index, val) {
            let trHtml = "<tr class='my-tr'>" +
                "<th>" + val['id'] + "</th>" +
                "<th>" + getContainerArea(val['row']) + "</th>" +
                "<th>" + val['row'] + "</th>" +
                "<th>" + val['column'] + "</th>" +
                "<th>" + val['layer'] + "</th>" +
                "<th>" + type[val['type']] + "</th>" +
                "<th>" + size[val['size']] + "</th>" +
                "</tr>";
            table.append(trHtml);
        });
        // 每次刷新完成以后，都要重新绑定事件监听器
        boundBtn();
    }

    /**
     * 用来分页展示JSON数据到货物的表格
     * @param content
     * @param pageNum
     * @param table
     */
    function getCargoDataByPage(content, pageNum, table){

        // 获得分页数据
        pageNum = parseInt(pageNum);
        pageNum--;

        // 先清空表格内容
        table.empty();

        // 这里截取获得需要页面的Json数据
        let temContent = content.slice(pageNum * 9, pageNum * 9 + 9);

        // 遍历添加内容
        $.each(temContent, function (index, val) {
            let tt;
            (val['containerId'] == '0')?tt="未装箱":tt=("已装箱，箱子id："+val['containerId']);
            let trHtml = "<tr class='my-tr'>" +
                "<th>" + val['cargoId'] + "</th>" +
                "<th>" + val['cargoType'] + "</th>" +
                "<th>" + val['cargoGross'] + "</th>" +
                "<th>" + tt + "</th>" +
                "</tr>";
            table.append(trHtml);
        });
        // 每次刷新完成以后，都要重新绑定事件监听器
        boundBth1();
    }

    /**
     * 页面按钮数字的变化
     */
    function paginationButtonChange() {
        $("#container-paging-ul").empty();
        if (containerPageNum > currentContainerPageMaxNum) {
            currentContainerPageMaxNum += 5;
            currentContainerPageMinxNum += 5;
            if (currentContainerPageMaxNum >= containerPageMaxNum) {
                currentContainerPageMaxNum = containerPageMaxNum;
                currentContainerPageMinxNum = containerPageMaxNum - 4;
            }
        }
        if (containerPageNum < currentContainerPageMinxNum) {
            currentContainerPageMaxNum -= 5;
            currentContainerPageMinxNum -= 5;
            if (currentContainerPageMinxNum <= 0) {
                currentContainerPageMaxNum = 5;
                currentContainerPageMinxNum = 1;
            }
        }

        for (let i = currentContainerPageMinxNum; i <= currentContainerPageMaxNum; i++) {
            $("#container-paging-ul").append("<li id='container-pager-id" + i + "'><a class='container-change-page'>" + i + "</a></li>");
        }

        boundPageBtn();
    }

    /**
     * 货物表页面按钮数字的变化
     *
     */
    function paginationButtonChangeCargo(){
        $("#cargo-paging-ul").empty();
        if (cargoPageNum > currentCargoPageMaxNum) {
            currentCargoPageMaxNum += 5;
            currentCargoPageMinxNum += 5;
            if (currentCargoPageMaxNum >= cargoPageMaxNum) {
                currentCargoPageMaxNum = cargoPageMaxNum;
                currentCargoPageMinxNum = cargoPageMaxNum - 4;
            }
        }
        if (cargoPageNum < currentCargoPageMinxNum) {
            currentCargoPageMaxNum -= 5;
            currentCargoPageMinxNum -= 5;
            if (currentCargoPageMinxNum <= 0) {
                currentCargoPageMaxNum = 5;
                currentCargoPageMinxNum = 1;
            }
        }

        for (let i = currentCargoPageMinxNum; i <= currentCargoPageMaxNum; i++) {
            $("#cargo-paging-ul").append("<li id='cargo-pager-id" + i + "'><a class='cargo-change-page'>" + i + "</a></li>");
        }

        boundCargoBtn();
    }

    /**
     * 绑定监听器--箱子表格，弹出窗口设置
     */
    function boundBtn() {
        $("#container-table-tbody .my-tr").click(function () {
            // 箱子里的货物
            let content;
            // 获取当前选中的箱子的ID
            let containerID = Number($(this).children().first().text());

            // ajax请求获得数据，通过箱子ID获得内部货物
            let cargoData = {
                "cargo":
                    [{
                        "cargoId": 10000000,
                        "typeId": 10000000,
                        "gross": 10,
                        "containerId": 10000000
                    }]
            };

            // 如果箱子中并没有货物
            if (cargoData.cargo.length <= 0) {
                content = "<div id='open-data'><h5 align='center'>这是一个空箱</h5>" +
                    "<button class='btn btn-info'>移动箱子</button><button class='btn btn-warning'>移除箱子</button></div>";

            } else {
                $.each(cargoData.cargo, function (index, val) {
                    content = "<div id='open-data'><p>货物&nbsp&nbspID：" + val['cargoId'] +
                        "</p><p>货物类型：" + val['typeId'] +
                        "</p><p>货物数量：" + val['gross'] +
                        "</p><button class='btn btn-info' id='move-container-btn'>移动箱子</button><button class='btn btn-warning' id='remove-container-btn'>移除箱子</button>" +
                        "</div>"
                });
            }
            layer.open({
                type: 1,
                title: "查询结果",
                closeBtn: 1,
                shadeClose: true,
                skin: 'layui-layer-molv',
                content: content
            });

            $("#move-container-btn").click(function () {
                let position;
                // ajax请求获得数据（可插入位置）
                let resultJson = {
                    "container": [{
                        "id": null,
                        "row": 1,
                        "column": 1,
                        "layer": 2,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 1, "column": 2, "layer": 2, "type": null, "size": null}, {
                        "id": null,
                        "row": 1,
                        "column": 3,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 1, "column": 4, "layer": 2, "type": null, "size": null}, {
                        "id": null,
                        "row": 1,
                        "column": 5,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 1, "column": 6, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 1,
                        "column": 7,
                        "layer": 2,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 1, "column": 8, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 1,
                        "column": 9,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 1, "column": 10, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 2,
                        "column": 1,
                        "layer": 2,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 2, "column": 2, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 2,
                        "column": 3,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 2, "column": 4, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 2,
                        "column": 5,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 2, "column": 6, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 2,
                        "column": 7,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 2, "column": 8, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 2,
                        "column": 9,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 2, "column": 10, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 3,
                        "column": 1,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 3, "column": 2, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 3,
                        "column": 3,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 3, "column": 4, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 3,
                        "column": 5,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 3, "column": 6, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 3,
                        "column": 7,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 3, "column": 8, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 3,
                        "column": 9,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 3, "column": 10, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 4,
                        "column": 1,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 4, "column": 2, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 4,
                        "column": 3,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 4, "column": 4, "layer": 2, "type": null, "size": null}, {
                        "id": null,
                        "row": 4,
                        "column": 5,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 4, "column": 6, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 4,
                        "column": 7,
                        "layer": 2,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 4, "column": 8, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 4,
                        "column": 9,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 4, "column": 10, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 5,
                        "column": 1,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 5, "column": 2, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 5,
                        "column": 3,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 5, "column": 4, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 5,
                        "column": 5,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 5, "column": 6, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 5,
                        "column": 7,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 5, "column": 8, "layer": 1, "type": null, "size": null}, {
                        "id": null,
                        "row": 5,
                        "column": 9,
                        "layer": 1,
                        "type": null,
                        "size": null
                    }, {"id": null, "row": 5, "column": 10, "layer": 1, "type": null, "size": null}]
                };
                let content = $("<div id='container-selectable-position'></div>");

                $.each(resultJson.container, function (index, val) {
                    let value = val['row'] + ',' + val['column'] + ',' + val['layer'];
                    let button = "<button class='btn btn-primary btn-sm container-selectable-position-btn'>" + value + "</button>";
                    content.append(button);
                });
                console.log(content.html());
                layer.open({
                    type: 1,
                    title: "可插入位置",
                    closeBtn: 1,
                    shadeClose: true,
                    skin: 'layui-layer-molv',
                    content: content.html(),
                    area: ['800px', 'auto']
                });
                // 位置按钮事件监听器
                $(".container-selectable-position-btn").click(function () {
                    position = $(this).text();
                    layer.confirm("确定要插入到该位置？", function (index) {
                        // ajax请求插入
                        alert(position);
                        layer.close(index);
                        layer.close(index - 1);
                        layer.close(index - 2);
                    }, function (index) {
                        layer.close(index);
                    });
                })
            });

            $("#remove-container-btn").click(function () {
                let content = "<p>是否确定移除该箱子？</p>";
                layer.confirm(content,
                    function (index) { // 确定后调用的函数
                        // ajax删除请求，通过箱子ID
                        layer.alert('移除成功');
                        layer.close(index);
                        layer.close(index - 1);
                        // 修改数据库后需要刷新 这里的json需要得到回传的json
                        refreshContainerTable(json1);
                    }, function () { // 取消调用的函数
                    });
            });

        });

        /**
         * 表格中按键的样式设计
         */
        $("#container-table-tbody .my-tr").hover(function () {
            $(this).siblings().removeClass("mouseoverColor");
            $(this).addClass("mouseoverColor");
        }, function () {
            $(this).removeClass("mouseoverColor");
        });
    }

    /**
     * 绑定监听器--货物表格，弹出窗口设置
     */
    function boundBth1() {
        $("#cargo-table-tbody .my-tr").click(function () {
            // 箱子里的货物
            let content;
            // 获取当前选中的箱子的ID
            let containerID = Number($(this).children().first().text());

            // ajax请求获得数据，通过箱子ID获得内部货物
            let cargoData = {
                "cargo":
                    [{
                        "cargoId": 10000000,
                        "typeId": 10000000,
                        "gross": 1088,
                        "containerId": 10000000
                    }]
            };

            {
                $.each(cargoData.cargo, function (index, val) {
                    content = "<div id='open-data-cargo'><p>货物&nbsp&nbspID：" + val['cargoId'] +
                        "</p><p>货物类型：" + val['typeId'] +
                        "</p><p>货物数量：" + val['gross'] +
                        "</p><button class='btn btn-info' id='move-cargo-btn'>插入箱子</button>" +
                        "</div>"
                });
            }
            layer.open({
                type: 1,
                title: "货物属性",
                closeBtn: 1,
                shadeClose: true,
                skin: 'layui-layer-molv',
                content: content
            });

            $("#move-cargo-btn").click(function () {
                let position;
                // ajax请求获得数据（可插入位置）
                let resultJson = {
                    "container": ["123456", "19988888", "1233333", "1232313", "12356778"]
                };
                let content = $("<div id='cargo-selectable-position'></div>");

                $.each(resultJson.container, function (index, val) {
                    let value = val;
                    let button = "<button class='btn btn-primary btn-sm cargo-selectable-position-btn'>" + value + "</button>";
                    content.append(button);
                });
                console.log(content.html());
                layer.open({
                    type: 1,
                    title: "可插入的箱子",
                    closeBtn: 1,
                    shadeClose: true,
                    skin: 'layui-layer-molv',
                    content: content.html(),
                    area: ['800px', 'auto']
                });
                // 位置按钮事件监听器
                $(".container-selectable-position-btn").click(function () {
                    position = $(this).text();
                    layer.confirm("确定要插入到该位置？", function (index) {
                        // ajax请求插入
                        alert(position);
                        layer.close(index);
                        layer.close(index - 1);
                        layer.close(index - 2);
                    }, function (index) {
                        layer.close(index);
                    });
                })
            });

        });

        /**
         * 表格中按键的样式设计
         */
        $("#cargo-table-tbody .my-tr").hover(function () {
            $(this).siblings().removeClass("mouseoverColor");
            $(this).addClass("mouseoverColor");
        }, function () {
            $(this).removeClass("mouseoverColor");
        });
    }

    /**
     * 绑定监听器--箱子表格分页按钮
     */
    function boundPageBtn() {
        $("#container-paging-ul li a").click(function () {
            $(this).parent().siblings().removeClass("active");
            $(this).parent().addClass("active");
            containerPageNum = $(this).text();
            getDataByPage(containerDataJson.container, containerPageNum, containerTable);
            altRowsjq();
        });
    }

    function boundCargoBtn() {
        $("#cargo-paging-ul li a").click(function () {
            $(this).parent().siblings().removeClass("active");
            $(this).parent().addClass("active");
            cargoPageNum = $(this).text();
            getCargoDataByPage(cargoDataJson.cargo, cargoPageNum, cargoTable);
            altRowsjq();
        });
    }

    /**
     * 每次根据查询接收到新的Json数据，就刷新箱子表
     *
     * @param resultJson json格式的箱子数据
     */
    function refreshContainerTable(resultJson) {

        let table = containerTable;
        let content = resultJson.container;
        let containerLength = content.length;

        // 遍历JSON数据，并对表格进行渲染
        if (containerLength <= 9 && containerLength > 0) {
            // 如果只有一页或者没有数据的话就隐藏分页
            $("#container-pagination").hide();
            getDataByPage(content, 1, table);
        } else if (containerLength <= 0) { // 如果没有数据则显示

            table.empty();
            $("#container-pagination").hide();
            table.append("<h3>没有查找到数据</h3>");

        } else { //如果需要分页，则进行分页展示

            // 获得分页按钮的父元素
            let parent = $("#container-paging-ul");
            parent.empty();
            // 分页数等于json里数据个数 /9 （单页表格最多展示9格数据）
            let pageLength = containerLength / 9;
            containerPageMaxNum = Math.ceil(pageLength);
            if (pageLength <= 5) { // 分页数在5页以内
                for (i = 0; i < pageLength; i++) {
                    parent.append("<li id='container-pager-id" + (i + 1) + "'><a class='container-change-page'>" + (i + 1) + "</a></li>");
                }
                $("#container-pager-id" + containerPageNum).addClass("active");
                currentContainerPageMaxNum = containerPageMaxNum;
                getDataByPage(content, containerPageNum, table); //每次默认显示第一页的内容
            } else { // 分页数多于5页
                for (i = 0; i < 5; i++) {
                    parent.append("<li id='container-pager-id" + (i + 1) + "'><a class='container-change-page'>" + (i + 1) + "</a></li>");
                }
                $("#container-pager-id" + containerPageNum).addClass("active");
                getDataByPage(content, containerPageNum, table); //每次默认显示第一页的内容
            }
            // 显示分页按钮
            $("#container-pagination").show();
            boundPageBtn();
        }
        // 动态修改表格的颜色
        altRowsjq();
    }

    /**
     * 刷新货物列表
     */
    function refreshCargoTable(resultJson) {


        let table = cargoTable;
        let content = resultJson.cargo;
        let containerLength = content.length;

        // 遍历JSON数据，并对表格进行渲染
        if (containerLength <= 9 && containerLength > 0) {
            // 如果只有一页或者没有数据的话就隐藏分页
            $("#cargo-pagination").hide();
            getCargoDataByPage(content, 1, table);
        } else if (containerLength <= 0) { // 如果没有数据则显示

            table.empty();
            $("#cargo-pagination").hide();
            table.append("<h3>没有查找到数据</h3>");

        } else { //如果需要分页，则进行分页展示

            // 获得分页按钮的父元素
            let parent = $("#cargo-paging-ul");
            parent.empty();
            // 分页数等于json里数据个数 /9 （单页表格最多展示9格数据）
            let pageLength = containerLength / 9;
            cargoPageMaxNum = Math.ceil(pageLength);
            if (pageLength <= 5) { // 分页数在5页以内
                for (i = 0; i < pageLength; i++) {
                    parent.append("<li id='cargo-pager-id" + (i + 1) + "'><a class='cargo-change-page'>" + (i + 1) + "</a></li>");
                }
                $("#cargo-pager-id" + cargoPageNum).addClass("active");
                currentCargoPageMaxNum = cargoPageMaxNum;
                getCargoDataByPage(content, 1, table); //每次默认显示第一页的内容
            } else { // 分页数多于5页
                for (i = 0; i < 5; i++) {
                    parent.append("<li id='cargo-pager-id" + (i + 1) + "'><a class='cargo-change-page'>" + (i + 1) + "</a></li>");
                }
                $("#cargo-pager-id" + cargoPageNum).addClass("active");
                getCargoDataByPage(content, 1, table); //每次默认显示第一页的内容
            }
            // 显示分页按钮
            $("#cargo-pagination").show();
            boundCargoBtn();
        }
        // 动态修改表格的颜色
        altRowsjq();
    }

    /**
     * 根据箱子的行号获得相应的区域
     *
     * @param row 箱子的行号
     */
    function getContainerArea(row) {
        if (row >= 1 && row <= 5) {
            return "空箱区";
        } else if (row >= 6 && row <= 15) {
            return "普通区";
        } else if (row >= 16 && row <= 21) {
            return "冷冻区";
        } else if (row >= 22 && row <= 27) {
            return "危险区";
        }
    }

    // 这里模拟首次ajax获得数据并渲染刷新表格
//            refreshContainerTable(json);

    /**
     * 箱子表向前翻页
     */
    $("#container-paging-pre-btn").click(function () {
        containerPageNum--;
        if (containerPageNum <= 0) {
            containerPageNum = 1;
        }
        paginationButtonChange();
        $("#container-pager-id" + containerPageNum).addClass("active");
        getDataByPage(containerDataJson.container, containerPageNum, containerTable);
        altRowsjq();
    });

    /**
     * 箱子表向后翻页
     */
    $("#container-paging-next-btn").click(function () {
        containerPageNum++;
        if (containerPageNum >= containerPageMaxNum) {
            containerPageNum = containerPageMaxNum;
        }
        paginationButtonChange();
        $("#container-pager-id" + containerPageNum).addClass("active");

        //
        getDataByPage(containerDataJson.container, containerPageNum, containerTable);
        altRowsjq();
    });

    /**
     * 货物表向前翻页
     */
    $("#cargo-paging-pre-btn").click(function () {
        cargoPageNum--;
        if (cargoPageNum <= 0) {
            cargoPageNum = 1;
        }
        paginationButtonChangeCargo()
        $("#cargo-pager-id" + cargoPageNum).addClass("active");
        getCargoDataByPage(cargoDataJson.cargo, cargoPageNum, cargoTable);
        altRowsjq();
    });

    /**
     * 货物表向后翻页
     */
    $("#cargo-paging-next-btn").click(function () {
        cargoPageNum++;
        if (cargoPageNum >= cargoPageMaxNum) {
            cargoPageNum = cargoPageMaxNum;
        }
        paginationButtonChangeCargo();
        $("#cargo-pager-id" + cargoPageNum).addClass("active");
        //
        getCargoDataByPage(cargoDataJson.cargo, cargoPageNum, cargoTable);
        altRowsjq();
    });

    /**
     * 箱子查询按钮，ajax实现
     */
    $("#search-container-btn").click(function () {
        // ajax查询，构建数据
//                let url = "api/container/get";
//                // type[-1:"无类型",0:"普通",1:"冷冻",2:"危险"]
//                // area[-1:"无类型",0:"空区",1:"普通",2:"冷冻",3:"危险"]
//                // size[-1:"无类型",0:"小",1:"大"]
//                let data = {
//                    type: ($("#search-container-type").val()),
//                    size: ($("#search-container-size").val()),
//                    area: ($("#search-container-area").val())
//                };
//
//                let func = function (resultJson) {
//                    containerDataJson = resultJson;
//                    refreshContainerTable(containerDataJson);
//                };
//
//                // ajax进行查询
//                $.ajax(getContainerInformation(url,data,func));

        alert("1");
        containerDataJson = json;
        refreshContainerTable(containerDataJson);

        // 刷新表格
    });


    /**
     * 货物查询按钮
     */
    $("#search-cargo-btn").click(function () {
        cargoDataJson = json4;
        refreshCargoTable(cargoDataJson);
    });


    /** ----------- 3D模型函数 ---------- **/
    /**
     * 3D模型初始化函数
     */
    // startThreejs(0);
    //
    // /**
    //  * 获得箱子数据
    //  * @returns {{type: string, dataType: string, url: string, data: {id: (*|jQuery), row: (*|jQuery), column: (*|jQuery), layer: (*|jQuery), type: (*|jQuery), size: (*|jQuery)}, success: success, error: error}}
    //  */
    // function getContainers() {
    //     return {
    //         type: "POST",
    //         dataType: "json",
    //         url: "/api/container/get",
    //         data: {
    //             id: $("#container-id").val(),
    //             row: $("#container-row").val(),
    //             column: $("#container-column").val(),
    //             layer: $("#container-layer").val(),
    //             type: $("#container-type").val(),
    //             size: $("#container-size").val()
    //         },
    //         success: function (result) {
    //             updateThreejs(result.container);
    //         },
    //         error: function () {
    //             alert("error!");
    //         }
    //     };
    // }
    //
    // /**
    //  * 3D模型换页函数
    //  */
    // $.ajax(getContainers());
    // $("#next-area-btn").click(function () {
    //     if (areatype < 3) {
    //         areatype++;
    //         $.get("/api/container/get", function (result) {
    //             updateThreejs(result.container);
    //         }, "json");
    //     }
    // });
    //
    // $("#prev-area-btn").click(function () {
    //     if (areatype > 0) {
    //         areatype--;
    //         $.get("/api/container/get", function (result) {
    //             updateThreejs(result.container);
    //         }, "json");
    //     }
    //
    // });
    // $("#container-query-btn").click(function () {
    //     $.ajax(getContainers());
    // });
    /** ----------- 3D模型函数 ---------- **/

});

// 测试用的json数据
json = {
    "container": [
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        }, {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        }, {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        }, {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        }, {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        }, {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        }, {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        }
    ]
};
json1 = {
    "container": [
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        }
    ]
};
json2 = {
    "container": [
        {
            "id": "10000000",
            "row": 1,
            "column": 1,
            "layer": 1,
            "type": 1,
            "size": 1
        },
        {
            "id": 10000001,
            "row": 1,
            "column": 4,
            "layer": 1,
            "type": 2,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        },
        {
            "id": 10000002,
            "row": 1,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 10000004,
            "row": 2,
            "column": 1,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 12121111,
            "row": 4,
            "column": 7,
            "layer": 1,
            "type": 0,
            "size": 0
        },
        {
            "id": 88888888,
            "row": 8,
            "column": 2,
            "layer": 2,
            "type": 0,
            "size": 0
        },
        {
            "id": 312312222,
            "row": 4,
            "column": 4,
            "layer": 1,
            "type": 1,
            "size": 0
        }
    ]
};
json3 = {"container": []};
json4 = {
    "cargo": [
        {
            "cargoId": "123123",
            "cargoType": "棉花",
            "cargoGross": "1200",
            "containerId": "10000000"
        },
        {
            "cargoId": "123123",
            "cargoType": "棉花",
            "cargoGross": "8888",
            "containerId": "0"
        },
        {
            "cargoId": "123123",
            "cargoType": "维尼熊玩偶",
            "cargoGross": "1200",
            "containerId": "10034500"
        },
        {
            "cargoId": "123123",
            "cargoType": "青蛙模型",
            "cargoGross": "1200",
            "containerId": "0"
        },
        {
            "cargoId": "123123",
            "cargoType": "华莱士公仔",
            "cargoGross": "80",
            "containerId": "1888800"
        },
        {
            "cargoId": "123123",
            "cargoType": "林则徐",
            "cargoGross": "9600",
            "containerId": "10222000"
        },                    {
            "cargoId": "123123",
            "cargoType": "腊肉",
            "cargoGross": "6666",
            "containerId": "0"
        },
        {
            "cargoId": "123123",
            "cargoType": "维尼熊玩偶",
            "cargoGross": "12000",
            "containerId": "10034500"
        },
        {
            "cargoId": "123123",
            "cargoType": "青蛙模型",
            "cargoGross": "1200",
            "containerId": "0"
        },
        {
            "cargoId": "123123",
            "cargoType": "朝鲜炒饭",
            "cargoGross": "80",
            "containerId": "188800"
        },
        {
            "cargoId": "123123",
            "cargoType": "腊肉",
            "cargoGross": "9600",
            "containerId": "102000"
        },
    ]
};