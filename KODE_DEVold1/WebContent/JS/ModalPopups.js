﻿var ModalPopupsDefaults = {
        shadow: !0,
        shadowSize: 5,
        shadowColor: "#333333",
        backgroundColor: "#CCCCCC",
        borderColor: "#999999",
        titleBackColor: "#C1D2E7",
        titleFontColor: "#15428B",
        popupBackColor: "#C7D6E9",
        popupFontColor: "black",
        footerBackColor: "#C1D2E7",
        footerFontColor: "#15428B",
        okButtonText: "OK",
        yesButtonText: "Yes",
        noButtonText: "No",
        cancelButtonText: "Cancel",
        fontFamily: "Verdana,Arial",
        fontSize: "9pt"
    },
    ModalPopups = {
        Init: function() {},
        SetDefaults: function(o) {
            o = o || {}, ModalPopupsDefaults.shadow = null != o.shadow ? o.shadow : ModalPopupsDefaults.shadow, ModalPopupsDefaults.shadowSize = null != o.shadowSize ? o.shadowSize : ModalPopupsDefaults.shadowSize, ModalPopupsDefaults.shadowColor = null != o.shadowColor ? o.shadowColor : ModalPopupsDefaults.shadowColor, ModalPopupsDefaults.backgroundColor = null != o.backgroundColor ? o.backgroundColor : ModalPopupsDefaults.backgroundColor, ModalPopupsDefaults.borderColor = null != o.borderColor ? o.borderColor : ModalPopupsDefaults.borderColor, ModalPopupsDefaults.okButtonText = null != o.okButtonText ? o.okButtonText : ModalPopupsDefaults.okButtonText, ModalPopupsDefaults.yesButtonText = null != o.yesButtonText ? o.yesButtonText : ModalPopupsDefaults.yesButtonText, ModalPopupsDefaults.noButtonText = null != o.noButtonText ? o.noButtonText : ModalPopupsDefaults.noButtonText, ModalPopupsDefaults.cancelButtonText = null != o.cancelButtonText ? o.cancelButtonText : ModalPopupsDefaults.cancelButtonText, ModalPopupsDefaults.titleBackColor = null != o.titleBackColor ? o.titleBackColor : ModalPopupsDefaults.titleBackColor, ModalPopupsDefaults.titleFontColor = null != o.titleFontColor ? o.titleFontColor : ModalPopupsDefaults.titleFontColor, ModalPopupsDefaults.popupBackColor = null != o.popupBackColor ? o.popupBackColor : ModalPopupsDefaults.popupBackColor, ModalPopupsDefaults.popupFontColor = null != o.popupFontColor ? o.popupFontColor : ModalPopupsDefaults.popupFontColor, ModalPopupsDefaults.footerBackColor = null != o.footerBackColor ? o.footerBackColor : ModalPopupsDefaults.footerBackColor, ModalPopupsDefaults.footerFontColor = null != o.footerFontColor ? o.footerFontColor : ModalPopupsDefaults.footerFontColor, ModalPopupsDefaults.fontFamily = null != o.fontFamily ? o.fontFamily : ModalPopupsDefaults.fontFamily, ModalPopupsDefaults.fontSize = null != o.fontSize ? o.fontSize : ModalPopupsDefaults.fontSize
        },
        Alert: function(o, t, e, l) {
            l = l || {}, t || (t = "Alert"), l.buttons = "ok", l.okButtonText = null != l.okButtonText ? l.okButtonText : ModalPopupsDefaults.okButtonText;
            var n = ModalPopups._createAllLayers(o, t, e, l),
                u = n[4];
            u.innerHTML = e, ModalPopups._styleAllLayers(o, l, n)
        },
        Confirm: function(o, t, e, l) {
            l = l || {}, t || (t = "Confirm"), l.buttons = "yes,no", l.yesButtonText = null != l.yesButtonText ? l.yesButtonText : ModalPopupsDefaults.yesButtonText, l.noButtonText = null != l.noButtonText ? l.noButtonText : ModalPopupsDefaults.noButtonText;
            var n = ModalPopups._createAllLayers(o, t, e, l),
                u = n[4];
            u.innerHTML = e, ModalPopups._styleAllLayers(o, l, n)
        },
        YesNoCancel: function(o, t, e, l) {
            l = l || {}, t || (t = "YesNoCancel"), l.buttons = "yes,no,cancel", l.yesButtonText = null != l.yesButtonText ? l.yesButtonText : ModalPopupsDefaults.yesButtonText, l.noButtonText = null != l.noButtonText ? l.noButtonText : ModalPopupsDefaults.noButtonText, l.cancelButtonText = null != l.cancelButtonText ? l.cancelButtonText : ModalPopupsDefaults.cancelButtonText;
            var n = ModalPopups._createAllLayers(o, t, e, l),
                u = n[4];
            u.innerHTML = e, ModalPopups._styleAllLayers(o, l, n)
        },
        Prompt: function(o, t, e, l) {
            l = l || {}, t || (t = "Prompt"), l.buttons = "ok,cancel", l.okButtonText = null != l.okButtonText ? l.okButtonText : "OK", l.cancelButtonText = null != l.cancelButtonText ? l.cancelButtonText : "Cancel";
            var n = ModalPopups._createAllLayers(o, t, e, l),
                u = n[4],
                p = "";
            null != l.width && (p = "width:95%;");
            var a = e + "<br/>";
            a += "<input type=text id='" + o + "_promptInput' value='' style='border: solid 1px #859DBE; " + p + "'>", u.innerHTML = a, ModalPopups._styleAllLayers(o, l, n), ModalPopupsSupport.findControl(o + "_promptInput").focus()
        },
        GetPromptInput: function(o) {
            var t = ModalPopupsSupport.findControl(o + "_promptInput");
            return t
        },
        GetPromptResult: function(o) {
            var t = ModalPopupsSupport.findControl(o + "_promptInput");
            return t
        },
        GetCustomControl: function(o) {
            return ModalPopupsSupport.findControl(o)
        },
        Indicator: function(o, t, e, l) {
            l = l || {}, t || (t = "Indicator"), null == l.buttons && (l.buttons = "");
            var n = ModalPopups._createAllLayers(o, t, e, l),
                u = n[4];
            u.innerHTML = e, ModalPopups._styleAllLayers(o, l, n)
        },
        Custom: function(o, t, e, l) {
            if (l = l || {}, t || (t = "Custom"), null == l.buttons) return void alert("buttons is a required parameter. ie: buttons: 'yes,no' or buttons: 'ok'.\nPossible buttons are yes, no, ok, cancel");
            var n = ModalPopups._createAllLayers(o, t, e, l),
                u = n[4];
            u.innerHTML = e, ModalPopups._styleAllLayers(o, l, n)
        },
        Close: function(o) {
            window.onresize = null, window.onscroll = null, document.body.removeChild(ModalPopupsSupport.findControl(o + "_background")), document.body.removeChild(ModalPopupsSupport.findControl(o + "_popup")), document.body.removeChild(ModalPopupsSupport.findControl(o + "_shadow"))
        },
        Cancel: function(o) {
            ModalPopups.Close(o)
        },
        _zIndex: 1e4,
        _createAllLayers: function(o, t, e, l) {
            var n = ModalPopupsSupport.makeLayer(o + "_background", !0, null),
                u = ModalPopupsSupport.makeLayer(o + "_popup", !0, null),
                p = ModalPopupsSupport.makeLayer(o + "_shadow", !0, null),
                a = ModalPopupsSupport.makeLayer(o + "_popupTitle", !0, u),
                r = ModalPopupsSupport.makeLayer(o + "_popupBody", !0, u),
                d = ModalPopupsSupport.makeLayer(o + "_popupFooter", !0, u),
                i = null != l.okButtonText ? l.okButtonText : ModalPopupsDefaults.okButtonText,
                s = null != l.yesButtonText ? l.yesButtonText : ModalPopupsDefaults.yesButtonText,
                c = null != l.noButtonText ? l.noButtonText : ModalPopupsDefaults.noButtonText,
                f = null != l.cancelButtonText ? l.cancelButtonText : ModalPopupsDefaults.cancelButtonText,
                h = null != l.onOk ? l.onOk : 'ModalPopups.Close("' + o + '");',
                m = null != l.onYes ? l.onYes : 'ModalPopups.Close("' + o + '");',
                y = null != l.onNo ? l.onNo : 'ModalPopups.Close("' + o + '");',
                g = null != l.onCancel ? l.onCancel : 'ModalPopups.Close("' + o + '");';
            a.innerHTML = "<table cellpadding='0' cellspacing='0' style='border: 0;' height='100%'><tr><td valign='middle'><b>" + t + "</b></td></tr></table>", d.innerHTML = "", l.fontFamily = null != l.fontFamily ? l.fontFamily : ModalPopupsDefaults.fontFamily;
            var M = l.buttons.split(",");
            for (x in M) "ok" == M[x] && (d.innerHTML += "<input name='" + o + "_okButton' id='" + o + "_okButton' type=button value='" + i + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + h + "'/>"), "yes" == M[x] && (d.innerHTML += "<input name='" + o + "_yesButton' id='" + o + "_yesButton' type=button value='" + s + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + m + "'/>"), "no" == M[x] && (d.innerHTML += "<input name='" + o + "_noButton' id='" + o + "_noButton' type=button value='" + c + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + y + "'/>"), "cancel" == M[x] && (d.innerHTML += "<input name='" + o + "_cancelButton' id='" + o + "_cancelButton' type=button value='" + f + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + g + "'/>");
            var P = new Array(n, u, p, a, r, d);
            return null != l.autoClose && setTimeout('ModalPopups.Close("' + o + '");', l.autoClose), P
        },
        _styleAllLayers: function(o, t, e) {
            var l = e,
                n = l[0],
                u = l[1],
                p = l[2],
                a = l[3],
                r = l[4],
                d = l[5];
            ModalPopups._zIndex += 3;
            var i = ModalPopups._zIndex;
            t.borderColor = null != t.borderColor ? t.borderColor : ModalPopupsDefaults.borderColor;
            var s = "display:inline; position:absolute; z-index: " + i + "; left:0px; top:0px; width:100%; height:100%; filter:alpha(opacity=70); opacity:0.7;";
            if (ModalPopupsSupport.isOlderIE()) {
                var c = ModalPopupsSupport.getViewportDimensions();
                s = "display:inline; position:absolute; z-index: 10; left:0px; top:0px; width:" + c.width + "px; height:" + c.height + "px; filter:alpha(opacity=70); opacity:0.7; overflow:hidden;"
            }
            var f = "display:inline; position:absolute; z-index: " + (i + 1) + ";",
                h = "display:inline; position:absolute; z-index: " + (i + 2) + "; background-color:white; color:black; border:solid 1px " + t.borderColor + "; padding:1px;";
            t.backgroundColor = null != t.backgroundColor ? t.backgroundColor : ModalPopupsDefaults.backgroundColor, s += " background-color:" + t.backgroundColor + ";", t.fontFamily = null != t.fontFamily ? t.fontFamily : ModalPopupsDefaults.fontFamily, t.fontSize = null != t.fontSize ? t.fontSize : ModalPopupsDefaults.fontSize;
            var m = "position: absolute; font-family:" + t.fontFamily + "; font-size:" + t.fontSize + "; padding: 5px; text-align:left;",
                y = "position: absolute; font-family:" + t.fontFamily + "; font-size:" + t.fontSize + "; padding: 5px; text-align:left;",
                g = "position: absolute; font-family:" + t.fontFamily + "; font-size:" + t.fontSize + "; padding: 5px; text-align:center;";
            ModalPopupsSupport.isIE ? (a.style.cssText = m, r.style.cssText = y, d.style.cssText = g) : (a.setAttribute("style", m), r.setAttribute("style", y), d.setAttribute("style", g)), t.titleBackColor = null != t.titleBackColor ? t.titleBackColor : ModalPopupsDefaults.titleBackColor, t.titleFontColor = null != t.titleFontColor ? t.titleFontColor : ModalPopupsDefaults.titleFontColor, t.popupBackColor = null != t.popupBackColor ? t.popupBackColor : ModalPopupsDefaults.popupBackColor, t.popupFontColor = null != t.popupFontColor ? t.popupFontColor : ModalPopupsDefaults.popupFontColor, t.footerBackColor = null != t.footerBackColor ? t.footerBackColor : ModalPopupsDefaults.footerBackColor, t.footerFontColor = null != t.footerFontColor ? t.footerFontColor : ModalPopupsDefaults.footerFontColor, m += " background-color:" + t.titleBackColor + ";", m += " color:" + t.titleFontColor + ";", y += " background-color:" + t.popupBackColor + ";", y += " color:" + t.popupFontColor + ";", g += " background-color:" + t.footerBackColor + ";", g += " color:" + t.footerFontColor + ";";
            var x = 0;
            ModalPopupsSupport.getLayerWidth(a.id) > x && (x = ModalPopupsSupport.getLayerWidth(a.id)), ModalPopupsSupport.getLayerWidth(r.id) > x && (x = ModalPopupsSupport.getLayerWidth(r.id)), ModalPopupsSupport.getLayerWidth(d.id) > x && (x = ModalPopupsSupport.getLayerWidth(d.id));
            var M = ModalPopupsSupport.getLayerHeight(a.id) + ModalPopupsSupport.getLayerHeight(r.id) + ModalPopupsSupport.getLayerHeight(d.id);
            t.width = null != t.width ? t.width : x + 4, t.height = null != t.height ? t.height : M;
            var P = ModalPopupsSupport.getLayerHeight(r.id);
            t.height > M && (P = t.height - ModalPopupsSupport.getLayerHeight(a.id) - ModalPopupsSupport.getLayerHeight(d.id), y += " height:" + P + "px;", M = ModalPopupsSupport.getLayerHeight(a.id) + P + ModalPopupsSupport.getLayerHeight(d.id)), m += " top:1px;", y += " top:" + ModalPopupsSupport.getLayerHeight(a.id) + "px;", g += " top:" + (ModalPopupsSupport.getLayerHeight(a.id) + P) + "px;", m += " width:" + (t.width - 10) + "px;", y += " width:" + (t.width - 10) + "px;", g += " width:" + (t.width - 10) + "px;";
            var C = ModalPopupsSupport.getFrameWidth(),
                B = ModalPopupsSupport.getFrameHeight();
            if (t.height < M && (t.height = M), t.top = null != t.top ? t.top : B / 2 - t.height / 2, t.left = null != t.left ? t.left : C / 2 - t.width / 2, m += " left:1px;", y += " left:1px;", g += " left:1px;", h += t.width ? " width:" + t.width + "px;" : " width:" + t.maxWidth + "px;", h += t.height ? " height:" + (t.height - 1) + "px;" : " height:" + (M - 1) + "px;", ModalPopupsSupport.isIE ? (a.style.cssText = m, r.style.cssText = y, d.style.cssText = g) : (a.setAttribute("style", m), r.setAttribute("style", y), d.setAttribute("style", g)), t.shadow = null != t.shadow ? t.shadow : ModalPopupsDefaults.shadow, t.shadowSize = null != t.shadowSize ? t.shadowSize : ModalPopupsDefaults.shadowSize, t.shadow ? (t.shadowSize = null != t.shadowSize ? t.shadowSize : ModalPopupsDefaults.shadowSize, t.shadowColor = null != t.shadowColor ? t.shadowColor : ModalPopupsDefaults.shadowColor, f += "background-color:" + t.shadowColor + ";", f += t.width ? " width:" + t.width + "px;" : " width:" + maxWidth + "px;", f += t.height ? " height:" + (t.height - 1) + "px;" : " height:" + M + "px;") : f += " display:none;", ModalPopupsSupport.isIE ? (u.style.cssText = h, p.style.cssText = f, n.style.cssText = s) : (u.setAttribute("style", h), p.setAttribute("style", f), n.setAttribute("style", s)), ModalPopupsSupport.isOlderIE()) {
                var c = ModalPopupsSupport.getViewportDimensions();
                n.innerHTML = "<div><iframe style='z-index:-1; position:absolute; top:0;left:0 display:none; display/**/:block; position:absolute; filter:mask(); width:" + c.width + "px; height:" + c.height + "px;' id='corr_bug_ie' src='../common/imgLay/spinner.gif'></iframe></div>"
            } else ModalPopupsSupport.centerElement(document.getElementById(o + "_background"), 0, !0);
            ModalPopupsSupport.centerElement(document.getElementById(o + "_popup"), 0, !1), t.shadow && ModalPopupsSupport.centerElement(document.getElementById(o + "_shadow"), t.shadowSize, !1), t.loadTextFile = null != t.loadTextFile ? t.loadTextFile : "", "" != t.loadTextFile && ModalPopups._loadTextFile(o, t, e, t.loadTextFile), window.onresize = function() {
                ModalPopupsSupport.centerElement(document.getElementById(o + "_background"), 0, !0), ModalPopupsSupport.centerElement(document.getElementById(o + "_popup"), 0, !1), t.shadow && ModalPopupsSupport.centerElement(document.getElementById(o + "_shadow"), t.shadowSize, !1)
            }, window.onscroll = function() {
                ModalPopupsSupport.centerElement(document.getElementById(o + "_background"), 0, !0), ModalPopupsSupport.centerElement(document.getElementById(o + "_popup"), 0, !1), t.shadow && ModalPopupsSupport.centerElement(document.getElementById(o + "_shadow"), t.shadowSize, !1)
            }
        },
        _loadTextFile: function(o, t, e, l) {
            var n = ModalPopupsSupport.getXmlHttp();
            n.open("GET", l, !0), n.onreadystatechange = function() {
                if (4 == n.readyState) {
                    var l = n.responseText.replace("\r\n", "<br>").replace("\n\r", "<br>").replace("\n", "<br>").replace("\r", "<br>"),
                        u = "<div style='overflow-y: scroll; position:absolute; top:5px; left:5px; height:" + (t.height - 65) + "px; width:" + (t.width - 10) + "px;'>";
                    u += l, u += "</div>", ModalPopups.GetCustomControl(o + "_popupBody").innerHTML = u, t.loadTextFile = "", ModalPopups._styleAllLayers(o, t, e)
                }
            }, n.send(null)
        }
    },
    ModalPopupsSupport = {
        isIE: function() {
            return window.ActiveXObject ? !0 : !1
        },
        isOlderIE: function() {
            var o = -1;
            if ("Microsoft Internet Explorer" == navigator.appName) {
                var t = navigator.userAgent,
                    e = new RegExp("MSIE ([0-9]{1,}[.0-9]{0,})");
                null != e.exec(t) && (o = parseFloat(RegExp.$1))
            }
            return o > -1 && 7 > o ? !0 : !1
        },
        makeLayer: function(o, t, e) {
            var l = document.createElement("div");
            return l.id = o, e ? e.appendChild(l) : document.body.appendChild(l), l
        },
        deleteLayer: function(o) {
            var t = findLayer(o);
            t && document.body.removeChild(t)
        },
        findLayer: function(o) {
            return document.all ? document.all[o] : document.getElementById(o)
        },
        findControl: function(o, t) {
            return null == t ? document.all ? document.all[o] : document.getElementById(o) : document.all ? document.all[o] : document.getElementById(o)
        },
        getLayerHeight: function(o) {
            return document.all ? gh = document.getElementById(o).offsetHeight : gh = document.getElementById(o).offsetHeight, gh
        },
        getLayerWidth: function(o) {
            return gw = document.getElementById(o).offsetWidth, gw
        },
        getViewportDimensions: function() {
            var o = 0,
                t = 0;
            return self.innerHeight ? (o = window.innerHeight, t = window.innerWidth) : document.documentElement && document.documentElement.clientHeight ? (o = document.documentElement.clientHeight, t = document.documentElement.clientWidth) : document.body && (o = document.body.clientHeight, t = document.body.clientWidth), {
                height: parseInt(o, 10),
                width: parseInt(t, 10)
            }
        },
        getScrollXY: function() {
            var o = 0,
                t = 0;
            return "number" == typeof window.pageYOffset ? (t = window.pageYOffset, o = window.pageXOffset) : document.body && (document.body.scrollLeft || document.body.scrollTop) ? (t = document.body.scrollTop, o = document.body.scrollLeft) : document.documentElement && (document.documentElement.scrollLeft || document.documentElement.scrollTop) && (t = document.documentElement.scrollTop, o = document.documentElement.scrollLeft), [o, t]
        },
        centerElement: function(o, t, e) {
            var l = ModalPopupsSupport.getViewportDimensions(),
                n = 0 == l.width ? 50 : parseInt((l.width - o.offsetWidth) / 2, 10),
                u = 0 == l.height ? 50 : parseInt((l.height - o.offsetHeight) / 2, 10),
                p = ModalPopupsSupport.getScrollXY();
            e || (o.style.left = n + t + "px"), o.style.top = u + t + p[1] + "px", o = null
        },
        readFile: function(o, t) {
            var e = getXmlHttp(),
                l = o + "?r=" + Math.random();
            e.open("GET", l, !0), e.onreadystatechange = function() {
                4 == e.readyState && (t.innerHTML = e.responseText)
            }, e.send(null)
        },
        getFrameWidth: function() {
            var o = document.documentElement.clientWidth;
            if (self.innerWidth) o = self.innerWidth;
            else if (document.documentElement && document.documentElement.clientWidth) o = document.documentElement.clientWidth;
            else {
                if (!document.body) return;
                o = document.body.clientWidth
            }
            return o
        },
        getFrameHeight: function() {
            var o = document.documentElement.clientHeight;
            if (self.innerWidth) o = self.innerHeight;
            else if (document.documentElement && document.documentElement.clientWidth) o = document.documentElement.clientHeight;
            else {
                if (!document.body) return;
                o = document.body.clientHeight
            }
            return o
        },
        getXmlHttp: function() {
            var o;
            try {
                o = new XMLHttpRequest
            } catch (t) {
                try {
                    o = new ActiveXObject("Msxml2.XMLHTTP")
                } catch (t) {
                    try {
                        o = new ActiveXObject("Microsoft.XMLHTTP")
                    } catch (t) {
                        return alert("Your browser does not support AJAX!"), !1
                    }
                }
            }
            return o
        }
    };