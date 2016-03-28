/**
 * Created by yeming370 on 10/12/15.
 */
$(function(){
    var fundResource = {
        submitFundConfig : function(params) {
            return $.post(Global.basePath + "/service/operation/p2p/config/submit-fund-config",params).success(function(data){
                    if(data.result == "success"){
                        SubmitLock.unlock();
                        alert("提交成功!");
                    } else{
                        SubmitLock.unlock();
                        alert("提交失败，请联系系统管理员!");
                    }
                }
            ).error(function (xhr) {
                    SubmitLock.unlock();
                    alert("提交失败\n " + JSON.parse(xhr.responseText).message);
                });
        },
        findCurrentValidFundConfig : function(params){
            return $.get(Global.basePath + "/service/operation/p2p/config/find-current-valid-fund-config", params);
        },
        getRenqiOptions : function(){
            return $.get(Global.basePath + "/service/operation/p2p/config/fund-group-query");
        },
        findProductIdByFundCode : function(params){
            return $.get(Global.basePath + "/service/operation/p2p/config/find-product-id-by-fund-code", params);
        },
        findAllFundComments : function (param) {
            return $.get(Global.basePath + "/service/operation/p2p/config/find-all-fund-comments", param);
        }
    };
    $('body').on('click',"#submitBtn",function(){
        var params = getParams();
        var errorMsg = checkParams(params);
        if(errorMsg){
            alert(errorMsg);
            return;
        };
        if(!SubmitLock.lock()) {
            alert('请不要重复提交');
            return;
        }
        fundResource.submitFundConfig(params);
    });
    $('body').on('click', ".searchProductId", function(){
        var $fundName = $(this).parent().next().children();
        var $fundComment = $(this).parent().siblings(".fundComment").children();
        var $productId = $(this).next();
        var params = {};
        var fundCodes = [];
        fundCodes.push($(this).prev().val());
        params["fundCodes"] = fundCodes;
        fundResource.findProductIdByFundCode(params).success(function(data){
            if(data.length==1){
                var name = data[0].displayName;
                var productId = data[0].id;
                var fundCode = data[0].fundCode;
                $productId.val(productId);
                $fundName.val(name);
                var param = {};
                param['fundCode'] = fundCode;
                fundResource.findAllFundComments(param).success(function(data){
                    if(data.length>0){
                        var item = data[0];
                        var appComment = item.appComment;
                        $fundComment.val(appComment);
                    }else{
                        alert("清先去基金点评统一管理界面录入相应基金的点评!");
                        $fundComment.val("");
                    }
                })
            }else{
                $productId.val("");
                $fundName.val("");
                $fundComment.val("");
                alert("基金代码不存在，请重新输入!");
            }
        }).error(function(xhr){
            $productId.val("");
            $fundName.val("");
            $fundComment.val("");
            alert(JSON.parse(xhr.responseText).message);
        });
    });

    $('body').on('click', ".topFundCode", function(){
        var topFundCode = $(this).prev().val().trim();
        if(topFundCode){
            var fundCodes = topFundCode.split(',');
            var params = {};
            params["fundCodes"] = fundCodes;
            fundResource.findProductIdByFundCode(params).success(function(data){
                if(data.length!=0){
                    var topFundName="",topProductId="";
                    for(var i=0; i<data.length; i++){
                        topFundName = topFundName + data[i].displayName;
                        topProductId = topProductId + data[i].id;
                        if(i != data.length-1){
                            topFundName = topFundName + ",";
                            topProductId = topProductId + ",";
                        }
                    }
                    $("#topFundName").val(topFundName);
                    $("#topProductId").val(topProductId);
                }else{
                    clearTopFund();
                    alert("基金代码不存在，请重新输入!");
                }
            }).error(function(xhr){
                clearTopFund();
                alert(JSON.parse(xhr.responseText).message);
            });
        }else{
            clearTopFund();
        }
    });

    function clearTopFund(){
        $("#topFundCode").val("");
        $("#topFundName").val("");
        $("#topProductId").val("");
    }

    function getParams(){
        var params = {};
        var fundCodes = [];
        var fundUnits = [];
        var fundUnitApps = [];
        var fundComments = [];
        var productIds = [];
        var names = [];
        //设置单品推荐
        for(var i=0;i<6;i++){
            var j=i+1;
            fundCodes[i] = $("#fundCode"+j).val();
            fundUnits[i] = $("#fundUnit" +j+ " option:selected").val();
            fundUnitApps[i] = $("#fundUnitApp" +j+ " option:selected").val();
            fundComments[i] = $("#fundComment" +j).val();
            productIds[i] = $("#productId" +j).val();
            names[i] = $("#fundName" +j).val();
        }
        //设置分类推荐
        var groupNos = [];
        var fundGroupUnits = [];
        var fundGroupUnitApps = [];
        var fundGroupComments = [];
        for(var i=0;i<2;i++){
            var j=i+1;
            groupNos[i] = $("#groupNo" +j+ " option:selected").val();
            fundGroupUnits[i] = $("#fundGroupUnit" +j+ " option:selected").val();
            fundGroupUnitApps[i] = $("#fundGroupUnitApp" +j+ " option:selected").val();
            fundGroupComments[i] = $("#fundGroupComment" +j).val();
        }
        //设置当前置顶基金
        var topFundCode = $("#topFundCode").val().trim();
        var topFundName = $("#topFundName").val().trim();
        var topProductId = $("#topProductId").val().trim();
        var topFundCodes = [];
        var topProductIds = [];
        var topProductNames = [];
        if(topFundCode){
            topFundCodes = topFundCode.split(",");
            params["topFundCodes"] = topFundCodes;
        }
        if(topFundName){
            topProductIds = topProductId.split(",");
            params["topProductIds"] = topProductIds;
        }
        if(topProductId){
            topProductNames = topFundName.split(",");
            params["topProductNames"] = topProductNames;
        }
        //设置搜索框默认显示词
        var searchKeyword = $("#searchKeyword").val();
        //设置首页关键词
        var indexKeywords = [];
        for(var i=0;i<3;i++){
            var j = i+1;
            indexKeywords[i] = $("#indexKeyword"+j+" option:selected").val();
        }
        params["fundCodes"] = fundCodes;
        params["fundUnits"] = fundUnits;
        params["fundUnitApps"] = fundUnitApps;
        params["fundComments"] = fundComments;
        params["names"] = names;
        params["productIds"] = productIds;
        params["groupNos"] = groupNos;
        params["fundGroupUnits"] = fundGroupUnits;
        params["fundGroupUnitApps"] = fundGroupUnitApps;
        params["fundGroupComments"] = fundGroupComments;
        params["indexKeywords"] = indexKeywords;
        params["searchKeyword"] = searchKeyword;
        //"1" 提交普通基金配置
        params["type"] = 1;
        return params;
    }
    function checkParams(params){
        var errorMsg = "";
        var fundComments = params["fundComments"];
        var fundGroupComments = params["fundGroupComments"];
        var productIds = params["productIds"];
        var fundCodes = params["fundCodes"];
        var groupNos = params["groupNos"];
        for(var i=0; i<productIds.length; i++){
            var productId = productIds[i].trim();
            var fundCode = fundCodes[i].trim();
            if(!productId || !fundCode){
                errorMsg = errorMsg + ("基金"+ (i+1)+"编码不存在!\n");
            }
        }
        for(var i=0; i<fundComments.length; i++){
            var fundComment = fundComments[i].trim();
            if(!fundComment){
                errorMsg = errorMsg + ("基金"+ (i+1)+"的评价不能为空!\n");
            }
        }
        for(var i=0; i<fundGroupComments.length; i++){
            var fundGroupComment = fundGroupComments[i].trim();
            if(!fundGroupComment){
                errorMsg = errorMsg + ("分类"+ (i+1)+"的评价不能为空!\n");
            }
        }
        //校验基金代码不能重复
        if(fundCodes[0]==fundCodes[1] || fundCodes[0]==fundCodes[2] || fundCodes[1]==fundCodes[2]){
            errorMsg = errorMsg + ("基金代码不能重复!\n");
        }
        //校验分类推荐不能选择相同的优选分组号
        if(groupNos[0]==groupNos[1]){
            errorMsg = errorMsg + ("分组号不能重复!\n");
        }
        return errorMsg;
    }

    function initData(){
        var params = {};
        //"1" 查询普通基金配置
        params['type'] = "1";
        fundResource.findCurrentValidFundConfig(params).success(function(data){
            data = JSON.parse(data.configJson);
           if(data.fundCodes){
               for(var i=0;i<6;i++){
                   $("#fundCode"+(i+1)).val(data.fundCodes[i]);
                   $("#fundName"+(i+1)).val(data.names[i]);
                   $("#productId"+(i+1)).val(data.productIds[i]);
                   $("#fundUnit"+(i+1)).val(data.fundUnits[i]);
                   $("#fundUnitApp"+(i+1)).val(data.fundUnitApps[i]);
                   $("#fundComment"+(i+1)).val(data.fundComments[i]);
               }
               for(var i=0;i<2;i++){
                   $("#groupNo"+(i+1)).val(data.groupNos[i]);
                   $("#fundGroupUnit"+(i+1)).val(data.fundGroupUnits[i]);
                   $("#fundGroupUnitApp"+(i+1)).val(data.fundGroupUnitApps[i]);
                   $("#fundGroupComment"+(i+1)).val(data.fundGroupComments[i]);
               }
               $("#topFundCode").val(data.topFundCodes.join(","));
               $("#topFundName").val(data.topProductNames.join(","));
               $("#topProductId").val(data.topProductIds.join(","));
               $("#searchKeyword").val(data.searchKeyword);
           }
            fundResource.getRenqiOptions().success(function(optionList){
                if(optionList.length==0){
                    alert("获取人气方案和主题失败!")
                }else{
                    $(".zhutiOptions").each(function(){
                        for(var i=0; i< optionList.length; i++){
                            var $option = $("<option></option>");
                            var item = optionList[i];
                            $option.val(item.id);
                            $option.html(item.groupName);
                            $(this).append($option);
                        }
                    });
                    if(data.indexKeywords){
                        for(var i=0;i<3;i++){
                            $("#indexKeyword"+(i+1)).val(data.indexKeywords[i]);
                        }
                    }
                }
            }).error(function(xhr){
                alert(JSON.parse(xhr.responseText).message);
            });
        }).error(function(xhr){
            alert(JSON.parse(xhr.responseText).message);
        });
    }

    initData();
});