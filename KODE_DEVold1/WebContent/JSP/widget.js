"use strict";
! function() {
    function e(t, n, o) {
        if (!(n.split(" ").length > 1)) return t.addEventListener ? (t.addEventListener(n, o, !1), !0) : t.attachEvent ? t.attachEvent("on" + n, o) : (t["on" + n] = o, this);
        for (var i = n.split(" "), s = 0; i > s; s++) e(t, i[s], o)
    }

    function t(e) {
        return document.getElementById(e)
    }

    function n(e) {
        var n = t(e),
            o = n.getContext("2d");
        return n.setAttribute("width", innerWidth), n.setAttribute("height", innerHeight), o.lineWidth = F, o.strokeStyle = I, o.fillStyle = S, o.font = E, o
    }

    function o(e, t, n, o, i) {
        function s(e) {
            return e = e || {}, [e.lineWidth || 2, e.strokeStyle || "#6c96c8", e.fillStyle || "transparent", e.globalAlpha || 1, e.globalCompositeOperation || "source-over", e.lineCap || "round", e.lineJoin || "round", e.font || '15px "Arial"']
        }

        function l(e, t) {
            e = e || s(), D.globalAlpha = e[3], D.globalCompositeOperation = e[4], D.lineCap = e[5], D.lineJoin = e[6], D.lineWidth = e[0], D.strokeStyle = e[1], D.fillStyle = e[2], D.font = e[7], t || (D.stroke(), D.fill())
        }
        var a = 10,
            r = Math.atan2(o - t, n - e);
        D.beginPath(), D.moveTo(e, t), D.lineTo(n, o), l(), D.beginPath(), D.moveTo(n, o), D.lineTo(n - a * Math.cos(r - Math.PI / 7), o - a * Math.sin(r - Math.PI / 7)), D.lineTo(n - a * Math.cos(r + Math.PI / 7), o - a * Math.sin(r + Math.PI / 7)), D.lineTo(n, o), D.lineTo(n - a * Math.cos(r - Math.PI / 7), o - a * Math.sin(r - Math.PI / 7)), l()
    }

    function i() {
        var e = P;
        e.isArc ? $.end() : e.isQuadraticCurve ? ne.end() : e.isBezierCurve && oe.end(), z.redraw(), Z.text && Z.text.length && (Z.appendPoints(), Z.onShapeUnSelected()), Z.showOrHideTextTools("hide")
    }

    function s() {
        i(), H.global.startingIndex = 0, t("copy-last").checked ? (O = b[b.length - 1], u(t("drag-last-path"), "DragLastPath")) : (O = b, u(t("drag-all-paths"), "DragAllPaths"))
    }

    function l() {
        i(), H.global.startingIndex = 0, t("copy-last").checked ? (b[b.length] = O, H.global = {
            prevX: 0,
            prevY: 0,
            startingIndex: b.length - 1
        }, H.dragAllPaths(0, 0), u(t("drag-last-path"), "DragLastPath")) : (H.global.startingIndex = b.length, b = b.concat(O), u(t("drag-all-paths"), "DragAllPaths"))
    }

    function a(e) {
        return parseInt(p(e).substring(0, 2), 16)
    }

    function r(e) {
        return parseInt(p(e).substring(2, 4), 16)
    }

    function c(e) {
        return parseInt(p(e).substring(4, 6), 16)
    }

    function p(e) {
        return "#" == e.charAt(0) ? e.substring(1, 7) : e
    }

    function d(e) {
        if (null === e || "object" != typeof e || "isActiveClone" in e) return e;
        if (e instanceof Date) var t = new e.constructor;
        else var t = e.constructor();
        for (var n in e) Object.prototype.hasOwnProperty.call(e, n) && (e.isActiveClone = null, t[n] = d(e[n]), delete e.isActiveClone);
        return t
    }

    function h(e) {
        return [a(e), r(e), c(e)]
    }

    function u(e, t) {
        i(), f(), P.set(t);
        var n = document.getElementsByClassName("selected-shape")[0];
        n && (n.className = n.className.replace(/selected-shape/g, "")), e.className || (e.className = ""), e.className += " selected-shape"
    }

    function f() {
        var e = t("additional-container"),
            n = t("colors-container"),
            o = t("marker-container"),
            i = t("marker-fill-colors"),
            s = t("pencil-container"),
            l = t("pencil-fill-colors"),
            a = t("line-width-container");
        e.style.display = n.style.display = i.style.display = o.style.display = l.style.display = s.style.display = a.style.display = "none"
    }

    function g(e) {
        return console.log("onkeydown"), he = e.which || e.keyCode || 0, 8 == he || 46 == he ? void v(e, he) : (e.metaKey && (A = !0, he = 17), void(A || 17 !== he || (A = !0)))
    }

    function v(e, t) {
        var n = !1,
            o = e.srcElement || e.target;
        return n = "INPUT" === o.tagName.toUpperCase() && ("TEXT" === o.type.toUpperCase() || "PASSWORD" === o.type.toUpperCase() || "FILE" === o.type.toUpperCase() || "SEARCH" === o.type.toUpperCase() || "EMAIL" === o.type.toUpperCase() || "NUMBER" === o.type.toUpperCase() || "DATE" === o.type.toUpperCase()) || "TEXTAREA" === o.tagName.toUpperCase() ? o.readOnly || o.disabled : !0, n && e.preventDefault(), n
    }

    function m(e) {
        return console.log("On click of canvas >>>>>>>>>>"), null != e.which || null == e.charCode && null == e.keyCode || (e.which = null != e.charCode ? e.charCode : e.keyCode), he = e.which || e.keyCode || 0, 13 === he && P.isText ? void Z.onReturnKeyPressed() : 8 == he || 46 == he ? void(v(e, he) && Z.writeText(Z.lastKeyPress, !0)) : A && 84 === he && P.isText ? void Z.showTextTools() : (A && 90 === he && b.length && (b.length = b.length - 1, z.redraw(), T(P.isDragAllPaths || P.isDragLastPath ? !0 : !1)), A && 65 === he && (H.global.startingIndex = 0, i(), u(t("drag-all-paths"), "DragAllPaths")), A && 67 === he && b.length && s(), A && 86 === he && O.length && (l(), T(P.isDragAllPaths || P.isDragLastPath ? !0 : !1)), "undefined" != typeof e.metaKey && e.metaKey === !1 && (A = !1, he = 17), void(17 === he && (A = !1)))
    }

    function y(e) {
        console.log("On key press >>>>"), null != e.which || null == e.charCode && null == e.keyCode || (e.which = null != e.charCode ? e.charCode : e.keyCode), he = e.which || e.keyCode || 0;
        var t = String.fromCharCode(he);
        /[a-zA-Z0-9-_ !?|\/'",.=:;(){}\[\]`~@#$%^&*+-]/.test(t) && Z.writeText(String.fromCharCode(he))
    }

    function x(e) {
        if (alert("onTextFromClipboard"), P.isText) {
            var t = void 0;
            window.clipboardData && window.clipboardData.getData ? t = window.clipboardData.getData("Text") : e.clipboardData && e.clipboardData.getData && (t = e.clipboardData.getData("text/plain")), t && t.length && Z.writeText(t)
        }
    }

    function T(e) {
        if (e && (fe = 0), fe != b.length) {
            for (var t = [], n = fe; n < b.length; n++) t[n - fe] = b[n];
            t.length && w({
                points: t || [],
                startIndex: fe
            }), (t.length || !b.length) && (fe = b.length)
        }
    }

    function w(e) {
        console.log("syncData method *************"), window.parent.postMessage({
            canvasDesignerSyncData: e,
            uid: ue
        }, "*")
    }
    var A, P = {
            isLine: !1,
            isArrow: !1,
            isArc: !1,
            isDragLastPath: !1,
            isDragAllPaths: !1,
            isRectangle: !1,
            isQuadraticCurve: !1,
            isBezierCurve: !1,
            isPencil: !1,
            isMarker: !0,
            isEraser: !1,
            isText: !1,
            isImage: !1,
            set: function(e) {
                var t = this;
                console.log("shape: ", e), t.isLine = t.isArrow = t.isArc = t.isDragLastPath = t.isDragAllPaths = t.isRectangle = t.isQuadraticCurve = t.isBezierCurve = t.isPencil = t.isMarker = t.isEraser = t.isText = t.isImage = !1, t["is" + e] = !0
            }
        },
        C = !1,
        b = [],
        k = t("code-text"),
        F = 2,
        I = "#6c96c8",
        S = "transparent",
        M = 1,
        X = "source-over",
        Y = "round",
        E = '15px "Arial"',
        L = "round",
        D = n("main-canvas"),
        R = n("temp-canvas"),
        B = {
            updateTextArea: function() {
                var e = B,
                    n = e.toFixed,
                    o = e.getPoint,
                    i = t("is-absolute-points").checked,
                    s = t("is-shorten-code").checked;
                i && s && e.absoluteShortened(), i && !s && e.absoluteNOTShortened(n), !i && s && e.relativeShortened(n, o), i || s || e.relativeNOTShortened(n, o)
            },
            toFixed: function(e) {
                return Number(e).toFixed(1)
            },
            getPoint: function(e, t, n) {
                return e = e > t ? n + " + " + (e - t) : t > e ? n + " - " + (t - e) : n
            },
            absoluteShortened: function() {
                var e, t = "",
                    n = b.length,
                    i = 0;
                for (i; n > i; i++) e = b[i], t += this.shortenHelper(e[0], e[1], e[2]);
                t = t.substr(0, t.length - 2), k.value = "var points = [" + t + "], length = points.length, point, p, i = 0;\n\n" + o.toString() + "\n\n" + this.forLoop, this.prevProps = null
            },
            absoluteNOTShortened: function(e) {
                var t, n, i, s = [];
                for (t = 0; t < b.length; t++) i = b[t], n = i[1], "pencil" === i[0] && (s[t] = ["context.beginPath();\ncontext.moveTo(" + n[0] + ", " + n[1] + ");\ncontext.lineTo(" + n[2] + ", " + n[3] + ");\n" + this.strokeOrFill(i[2])]), "marker" === i[0] && (s[t] = ["context.beginPath();\ncontext.moveTo(" + n[0] + ", " + n[1] + ");\ncontext.lineTo(" + n[2] + ", " + n[3] + ");\n" + this.strokeOrFill(i[2])]), "eraser" === i[0] && (s[t] = ["context.beginPath();\ncontext.moveTo(" + n[0] + ", " + n[1] + ");\ncontext.lineTo(" + n[2] + ", " + n[3] + ");\n" + this.strokeOrFill(i[2])]), "line" === i[0] && (s[t] = ["context.beginPath();\ncontext.moveTo(" + n[0] + ", " + n[1] + ");\ncontext.lineTo(" + n[2] + ", " + n[3] + ");\n" + this.strokeOrFill(i[2])]), "text" === i[0] && (alert("Text p[0]"), s[t] = [this.strokeOrFill(i[2]) + "\ncontext.fillText(" + n[0] + ", " + n[1] + ", " + n[2] + ");"]), "arrow" === i[0] && (s[t] = ["drawArrow(" + n[0] + ", " + n[1] + ", " + n[2] + ", " + n[3] + ", '" + i[2].join("','") + "');"]), "arc" === i[0] && (s[t] = ["context.beginPath(); \ncontext.arc(" + e(n[0]) + "," + e(n[1]) + "," + e(n[2]) + "," + e(n[3]) + ", 0," + n[4] + "); \n" + this.strokeOrFill(i[2])]), "rect" === i[0] && (s[t] = [this.strokeOrFill(i[2]) + "\ncontext.strokeRect(" + n[0] + ", " + n[1] + "," + n[2] + "," + n[3] + ");\ncontext.fillRect(" + n[0] + ", " + n[1] + "," + n[2] + "," + n[3] + ");"]), "quadratic" === i[0] && (s[t] = ["context.beginPath();\ncontext.moveTo(" + n[0] + ", " + n[1] + ");\ncontext.quadraticCurveTo(" + n[2] + ", " + n[3] + ", " + n[4] + ", " + n[5] + ");\n" + this.strokeOrFill(i[2])]), "bezier" === i[0] && (s[t] = ["context.beginPath();\ncontext.moveTo(" + n[0] + ", " + n[1] + ");\ncontext.bezierCurveTo(" + n[2] + ", " + n[3] + ", " + n[4] + ", " + n[5] + ", " + n[6] + ", " + n[7] + ");\n" + this.strokeOrFill(i[2])]);
                k.value = s.join("\n\n") + this.strokeFillText + "\n\n" + o.toString(), this.prevProps = null
            },
            relativeShortened: function(e, t) {
                var n, i, s = 0,
                    l = b.length,
                    a = "",
                    r = 0,
                    c = 0;
                for (s; l > s; s++) i = b[s], n = i[1], 0 === s && (r = n[0], c = n[1]), "text" === i[0] && (r = n[1], c = n[2]), "pencil" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y")], i[2])), "marker" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y")], i[2])), "eraser" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y")], i[2])), "line" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y")], i[2])), "arrow" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y")], i[2])), "text" === i[0] && (a += this.shortenHelper(i[0], [n[0], t(n[1], r, "x"), t(n[2], c, "y")], i[2])), "arc" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), n[2], n[3], n[4]], i[2])), "rect" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y")], i[2])), "quadratic" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y"), t(n[4], r, "x"), t(n[5], c, "y")], i[2])), "bezier" === i[0] && (a += this.shortenHelper(i[0], [t(n[0], r, "x"), t(n[1], c, "y"), t(n[2], r, "x"), t(n[3], c, "y"), t(n[4], r, "x"), t(n[5], c, "y"), t(n[6], r, "x"), t(n[7], c, "y")], i[2]));
                a = a.substr(0, a.length - 2), k.value = "var x = " + r + ", y = " + c + ", points = [" + a + "], length = points.length, point, p, i = 0;\n\n" + o.toString() + "\n\n" + this.forLoop, this.prevProps = null
            },
            relativeNOTShortened: function(e, t) {
                var n, i, s, l = b.length,
                    a = "",
                    r = 0,
                    c = 0;
                for (n = 0; l > n; n++) s = b[n], i = s[1], 0 === n && (r = i[0], c = i[1], "text" === s[0] && (r = i[1], c = i[2]), a = "var x = " + r + ", y = " + c + ";\n\n"), "arc" === s[0] && (a += "context.beginPath();\ncontext.arc(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ", " + i[2] + ", " + i[3] + ", 0, " + i[4] + ");\n" + this.strokeOrFill(s[2])), "pencil" === s[0] && (a += "context.beginPath();\ncontext.moveTo(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ");\ncontext.lineTo(" + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ");\n" + this.strokeOrFill(s[2])), "marker" === s[0] && (a += "context.beginPath();\ncontext.moveTo(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ");\ncontext.lineTo(" + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ");\n" + this.strokeOrFill(s[2])), "eraser" === s[0] && (a += "context.beginPath();\ncontext.moveTo(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ");\ncontext.lineTo(" + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ");\n" + this.strokeOrFill(s[2])), "line" === s[0] && (a += "context.beginPath();\ncontext.moveTo(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ");\ncontext.lineTo(" + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ");\n" + this.strokeOrFill(s[2])), "arrow" === s[0] && (a += "drawArrow(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ", " + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ", '" + s[2].join("','") + "');\n"), "text" === s[0] && (a += this.strokeOrFill(s[2]) + "\ncontext.fillText(" + i[0] + ", " + t(i[1], r, "x") + ", " + t(i[2], c, "y") + ");", console.log("output " + a)), "rect" === s[0] && (a += this.strokeOrFill(s[2]) + "\ncontext.strokeRect(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ", " + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ");\ncontext.fillRect(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ", " + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ");"), "quadratic" === s[0] && (a += "context.beginPath();\ncontext.moveTo(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ");\ncontext.quadraticCurveTo(" + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ", " + t(i[4], r, "x") + ", " + t(i[5], c, "y") + ");\n" + this.strokeOrFill(s[2])), "bezier" === s[0] && (a += "context.beginPath();\ncontext.moveTo(" + t(i[0], r, "x") + ", " + t(i[1], c, "y") + ");\ncontext.bezierCurveTo(" + t(i[2], r, "x") + ", " + t(i[3], c, "y") + ", " + t(i[4], r, "x") + ", " + t(i[5], c, "y") + ", " + t(i[6], r, "x") + ", " + t(i[7], c, "y") + ");\n" + this.strokeOrFill(s[2])), n !== l - 1 && (a += "\n\n");
                k.value = a + this.strokeFillText + "\n\n" + o.toString(), this.prevProps = null
            },
            forLoop: 'for(i; i < length; i++) {\n    p = points[i];\n    point = p[1];\n    context.beginPath();\n\n    if(p[2]) { \n	context.lineWidth = p[2][0];\n	context.strokeStyle = p[2][1];\n	context.fillStyle = p[2][2];\n	context.globalAlpha = p[2][3];\n	context.globalCompositeOperation = p[2][4];\n	context.lineCap = p[2][5];\n	context.lineJoin = p[2][6];\n	context.font = p[2][7];\n    }\n\n    if(p[0] === "line") { \n	context.moveTo(point[0], point[1]);\n	context.lineTo(point[2], point[3]);\n    }\n\n    if(p[0] === "arrow") { \n	drawArrow(point[0], point[1], point[2], point[3], p[2]);\n    }\n\n    if(p[0] === "pencil") { \n	context.moveTo(point[0], point[1]);\n	context.lineTo(point[2], point[3]);\n    }\n\n    if(p[0] === "marker") { \n	context.moveTo(point[0], point[1]);\n	context.lineTo(point[2], point[3]);\n    }\n\n    if(p[0] === "text") { \n	context.fillText(point[0], point[1], point[2]);\n    }\n\n    if(p[0] === "eraser") { \n	context.moveTo(point[0], point[1]);\n	context.lineTo(point[2], point[3]);\n    }\n\n    if(p[0] === "arc") context.arc(point[0], point[1], point[2], point[3], 0, point[4]); \n\n    if(p[0] === "rect") {\n	context.strokeRect(point[0], point[1], point[2], point[3]);\n	context.fillRect(point[0], point[1], point[2], point[3]);\n    }\n\n    if(p[0] === "quadratic") {\n	context.moveTo(point[0], point[1]);\n	context.quadraticCurveTo(point[2], point[3], point[4], point[5]);\n    }\n\n    if(p[0] === "bezier") {\n	context.moveTo(point[0], point[1]);\n	context.bezierCurveTo(point[2], point[3], point[4], point[5], point[6], point[7]);\n    }\n\n    context.stroke();\n    context.fill();\n}',
            strokeFillText: "\n\nfunction strokeOrFill(lineWidth, strokeStyle, fillStyle, globalAlpha, globalCompositeOperation, lineCap, lineJoin, font) { \n    if(lineWidth) { \n	context.globalAlpha = globalAlpha;\n	context.globalCompositeOperation = globalCompositeOperation;\n	context.lineCap = lineCap;\n	context.lineJoin = lineJoin;\n	context.lineWidth = lineWidth;\n	context.strokeStyle = strokeStyle;\n	context.fillStyle = fillStyle;\n	context.font = font;\n    } \n\n    context.stroke();\n    context.fill();\n}",
            strokeOrFill: function(e) {
                return this.prevProps && this.prevProps === e.join(",") ? "strokeOrFill();" : (this.prevProps = e.join(","), "strokeOrFill('" + e.join("', '") + "');")
            },
            prevProps: null,
            shortenHelper: function(e, t, n) {
                var o = "['" + e + "', [" + t.join(", ") + "]";
                return this.prevProps && this.prevProps === n.join(",") || (this.prevProps = n.join(","), o += ", ['" + n.join("', '") + "']"), o + "], "
            }
        },
        O = [],
        z = {
            redraw: function() {
                R.clearRect(0, 0, innerWidth, innerHeight), D.clearRect(0, 0, innerWidth, innerHeight);
                var e, t, n = b.length;
                for (e = 0; n > e; e++) t = b[e], t && t.length && this[t[0]] && this[t[0]](D, t[1], t[2])
            },
            getOptions: function(e) {
                return e = e || {}, [e.lineWidth || F, e.strokeStyle || I, e.fillStyle || S, e.globalAlpha || M, e.globalCompositeOperation || X, e.lineCap || Y, e.lineJoin || L, e.font || E]
            },
            handleOptions: function(e, t, n) {
                t = t || this.getOptions(), e.globalAlpha = t[3], e.globalCompositeOperation = t[4], e.lineCap = t[5], e.lineJoin = t[6], e.lineWidth = t[0], e.strokeStyle = t[1], e.fillStyle = t[2], e.font = t[7], n || (e.stroke(), e.fill())
            },
            line: function(e, t, n) {
                e.beginPath(), e.moveTo(t[0], t[1]), e.lineTo(t[2], t[3]), this.handleOptions(e, n)
            },
            marker: function(e, t, n) {
                e.beginPath(), e.moveTo(t[0], t[1]), e.lineTo(t[2], t[3]), this.handleOptions(e, n)
            },
            arrow: function(e, t, n) {
                var o = t[0],
                    i = t[1],
                    s = t[2],
                    l = t[3],
                    a = ee.arrowSize;
                10 == a && (a = 5 * (n ? n[0] : F));
                var r = Math.atan2(l - i, s - o);
                e.beginPath(), e.moveTo(o, i), e.lineTo(s, l), this.handleOptions(e, n), e.beginPath(), e.moveTo(s, l), e.lineTo(s - a * Math.cos(r - Math.PI / 7), l - a * Math.sin(r - Math.PI / 7)), e.lineTo(s - a * Math.cos(r + Math.PI / 7), l - a * Math.sin(r + Math.PI / 7)), e.lineTo(s, l), e.lineTo(s - a * Math.cos(r - Math.PI / 7), l - a * Math.sin(r - Math.PI / 7)), this.handleOptions(e, n)
            },
            text: function(e, t, n) {
                console.log("text function text: function(context, point, options)"), this.handleOptions(e, n), e.fillStyle = Z.getFillColor(n[2]), e.fillText(t[0].substr(1, t[0].length - 2), t[1], t[2])
            },
            arc: function(e, t, n) {
                e.beginPath(), e.arc(t[0], t[1], t[2], t[3], 0, t[4]), this.handleOptions(e, n)
            },
            rect: function(e, t, n) {
                this.handleOptions(e, n, !0), e.strokeRect(t[0], t[1], t[2], t[3]), e.fillRect(t[0], t[1], t[2], t[3])
            },
            image: function(e, t, n) {
                this.handleOptions(e, n, !0);
                var o = le.images[t[5]];
                if (!o) {
                    var o = new Image;
                    return o.onload = function() {
                        var n = le.images.length;
                        le.lastImageURL = o.src, le.lastImageIndex = n, le.images.push(o), e.drawImage(o, t[1], t[2], t[3], t[4])
                    }, void(o.src = t[0])
                }
                e.drawImage(o, t[1], t[2], t[3], t[4])
            },
            quadratic: function(e, t, n) {
                e.beginPath(), e.moveTo(t[0], t[1]), e.quadraticCurveTo(t[2], t[3], t[4], t[5]), this.handleOptions(e, n)
            },
            bezier: function(e, t, n) {
                e.beginPath(), e.moveTo(t[0], t[1]), e.bezierCurveTo(t[2], t[3], t[4], t[5], t[6], t[7]), this.handleOptions(e, n)
            }
        },
        H = {
            global: {
                prevX: 0,
                prevY: 0,
                ismousedown: !1,
                pointsToMove: "all",
                startingIndex: 0
            },
            mousedown: function(e) {
                A && (s(), l(), A = !1);
                var t = H,
                    n = t.global,
                    o = e.pageX - pe.offsetLeft,
                    i = e.pageY - pe.offsetTop;
                if (n.prevX = o, n.prevY = i, n.pointsToMove = "all", b.length) {
                    var a = b[b.length - 1],
                        r = a[1];
                    "line" === a[0] && (t.isPointInPath(o, i, r[0], r[1]) && (n.pointsToMove = "head"), t.isPointInPath(o, i, r[2], r[3]) && (n.pointsToMove = "tail")), "arrow" === a[0] && (t.isPointInPath(o, i, r[0], r[1]) && (n.pointsToMove = "head"), t.isPointInPath(o, i, r[2], r[3]) && (n.pointsToMove = "tail")), "rect" === a[0] && (t.isPointInPath(o, i, r[0], r[1]) && (n.pointsToMove = "stretch-first"), t.isPointInPath(o, i, r[0] + r[2], r[1]) && (n.pointsToMove = "stretch-second"), t.isPointInPath(o, i, r[0], r[1] + r[3]) && (n.pointsToMove = "stretch-third"), t.isPointInPath(o, i, r[0] + r[2], r[1] + r[3]) && (n.pointsToMove = "stretch-last")), "image" === a[0] && (t.isPointInPath(o, i, r[1], r[2]) && (n.pointsToMove = "stretch-first"), t.isPointInPath(o, i, r[1] + r[3], r[2]) && (n.pointsToMove = "stretch-second"), t.isPointInPath(o, i, r[1], r[2] + r[4]) && (n.pointsToMove = "stretch-third"), t.isPointInPath(o, i, r[1] + r[3], r[2] + r[4]) && (n.pointsToMove = "stretch-last")), "quadratic" === a[0] && (t.isPointInPath(o, i, r[0], r[1]) && (n.pointsToMove = "starting-points"), t.isPointInPath(o, i, r[2], r[3]) && (n.pointsToMove = "control-points"), t.isPointInPath(o, i, r[4], r[5]) && (n.pointsToMove = "ending-points")), "bezier" === a[0] && (t.isPointInPath(o, i, r[0], r[1]) && (n.pointsToMove = "starting-points"), t.isPointInPath(o, i, r[2], r[3]) && (n.pointsToMove = "1st-control-points"), t.isPointInPath(o, i, r[4], r[5]) && (n.pointsToMove = "2nd-control-points"), t.isPointInPath(o, i, r[6], r[7]) && (n.pointsToMove = "ending-points"))
                }
                n.ismousedown = !0
            },
            mouseup: function() {
                var e = this.global;
                P.isDragLastPath && (R.clearRect(0, 0, innerWidth, innerHeight), D.clearRect(0, 0, innerWidth, innerHeight), this.end()), e.ismousedown = !1
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this.global;
                z.redraw(), o.ismousedown && this.dragShape(t, n), P.isDragLastPath && this.init()
            },
            init: function() {
                if (b.length) {
                    var e = b[b.length - 1],
                        t = e[1],
                        n = this.global;
                    n.ismousedown ? R.fillStyle = "rgba(255,85 ,154,.9)" : R.fillStyle = "rgba(255,85 ,154,.4)", "quadratic" === e[0] && (R.beginPath(), R.arc(t[0], t[1], 10, 2 * Math.PI, 0, !1), R.arc(t[2], t[3], 10, 2 * Math.PI, 0, !1), R.arc(t[4], t[5], 10, 2 * Math.PI, 0, !1), R.fill()), "bezier" === e[0] && (R.beginPath(), R.arc(t[0], t[1], 10, 2 * Math.PI, 0, !1), R.arc(t[2], t[3], 10, 2 * Math.PI, 0, !1), R.arc(t[4], t[5], 10, 2 * Math.PI, 0, !1), R.arc(t[6], t[7], 10, 2 * Math.PI, 0, !1), R.fill()), "line" === e[0] && (R.beginPath(), R.arc(t[0], t[1], 10, 2 * Math.PI, 0, !1), R.arc(t[2], t[3], 10, 2 * Math.PI, 0, !1), R.fill()), "arrow" === e[0] && (R.beginPath(), R.arc(t[0], t[1], 10, 2 * Math.PI, 0, !1), R.arc(t[2], t[3], 10, 2 * Math.PI, 0, !1), R.fill()), "text" === e[0] && (R.font = "15px Verdana", R.fillText(t[0], t[1], t[2])), "rect" === e[0] && (R.beginPath(), R.arc(t[0], t[1], 10, 2 * Math.PI, 0, !1), R.fill(), R.beginPath(), R.arc(t[0] + t[2], t[1], 10, 2 * Math.PI, 0, !1), R.fill(), R.beginPath(), R.arc(t[0], t[1] + t[3], 10, 2 * Math.PI, 0, !1), R.fill(), R.beginPath(), R.arc(t[0] + t[2], t[1] + t[3], 10, 2 * Math.PI, 0, !1), R.fill()), "image" === e[0] && (R.beginPath(), R.arc(t[1], t[2], 10, 2 * Math.PI, 0, !1), R.fill(), R.beginPath(), R.arc(t[1] + t[3], t[2], 10, 2 * Math.PI, 0, !1), R.fill(), R.beginPath(), R.arc(t[1], t[2] + t[4], 10, 2 * Math.PI, 0, !1), R.fill(), R.beginPath(), R.arc(t[1] + t[3], t[2] + t[4], 10, 2 * Math.PI, 0, !1), R.fill())
                }
            },
            isPointInPath: function(e, t, n, o) {
                return e > n - 10 && n + 10 > e && t > o - 10 && o + 10 > t
            },
            getPoint: function(e, t, n) {
                return e = e > t ? n + (e - t) : n - (t - e)
            },
            getXYWidthHeight: function(e, t, n, o, i) {
                return "stretch-first" == i.pointsToMove && (e > n ? (i.x = i.x + (e - n), i.width = i.width - (e - n)) : (i.x = i.x - (n - e), i.width = i.width + (n - e)), t > o ? (i.y = i.y + (t - o), i.height = i.height - (t - o)) : (i.y = i.y - (o - t), i.height = i.height + (o - t))), "stretch-second" == i.pointsToMove && (e > n ? i.width = i.width + (e - n) : i.width = i.width - (n - e), o > t ? (i.y = i.y + (t - o), i.height = i.height - (t - o)) : (i.y = i.y - (o - t), i.height = i.height + (o - t))), "stretch-third" == i.pointsToMove && (e > n ? (i.x = i.x + (e - n), i.width = i.width - (e - n)) : (i.x = i.x - (n - e), i.width = i.width + (n - e)), o > t ? i.height = i.height + (t - o) : i.height = i.height - (o - t)), i
            },
            dragShape: function(e, t) {
                if (this.global.ismousedown) {
                    R.clearRect(0, 0, innerWidth, innerHeight), P.isDragLastPath && this.dragLastPath(e, t), P.isDragAllPaths && this.dragAllPaths(e, t);
                    var n = this.global;
                    n.prevX = e, n.prevY = t
                }
            },
            end: function() {
                if (b.length) {
                    R.clearRect(0, 0, innerWidth, innerHeight);
                    var e = b[b.length - 1];
                    z[e[0]](D, e[1], e[2])
                }
            },
            dragAllPaths: function(e, t) {
                var n, o, i = this.global,
                    s = i.prevX,
                    l = i.prevY,
                    a = b.length,
                    r = this.getPoint,
                    c = i.startingIndex;
                for (c; a > c; c++) n = b[c], o = n[1], "line" === n[0] && (b[c] = [n[0],
                    [r(e, s, o[0]), r(t, l, o[1]), r(e, s, o[2]), r(t, l, o[3])], n[2]
                ]), "arrow" === n[0] && (b[c] = [n[0],
                    [r(e, s, o[0]), r(t, l, o[1]), r(e, s, o[2]), r(t, l, o[3])], n[2]
                ]), "text" === n[0] && (b[c] = [n[0],
                    [o[0], r(e, s, o[1]), r(t, l, o[2])], n[2]
                ]), "arc" === n[0] && (b[c] = [n[0],
                    [r(e, s, o[0]), r(t, l, o[1]), o[2], o[3], o[4]], n[2]
                ]), "rect" === n[0] && (b[c] = [n[0],
                    [r(e, s, o[0]), r(t, l, o[1]), o[2], o[3]], n[2]
                ]), "image" === n[0] && (b[c] = [n[0],
                    [o[0], r(e, s, o[1]), r(t, l, o[2]), o[3], o[4], o[5]], n[2]
                ]), "quadratic" === n[0] && (b[c] = [n[0],
                    [r(e, s, o[0]), r(t, l, o[1]), r(e, s, o[2]), r(t, l, o[3]), r(e, s, o[4]), r(t, l, o[5])], n[2]
                ]), "bezier" === n[0] && (b[c] = [n[0],
                    [r(e, s, o[0]), r(t, l, o[1]), r(e, s, o[2]), r(t, l, o[3]), r(e, s, o[4]), r(t, l, o[5]), r(e, s, o[6]), r(t, l, o[7])], n[2]
                ])
            },
            dragLastPath: function(e, t) {
                var n = this.global,
                    o = n.prevX,
                    i = n.prevY,
                    s = b[b.length - 1],
                    l = s[1],
                    a = this.getPoint,
                    r = this.getXYWidthHeight,
                    c = "all" === n.pointsToMove;
                if ("line" === s[0] && (("head" === n.pointsToMove || c) && (l[0] = a(e, o, l[0]), l[1] = a(t, i, l[1])), ("tail" === n.pointsToMove || c) && (l[2] = a(e, o, l[2]), l[3] = a(t, i, l[3])), b[b.length - 1] = [s[0], l, s[2]]), "arrow" === s[0] && (("head" === n.pointsToMove || c) && (l[0] = a(e, o, l[0]), l[1] = a(t, i, l[1])), ("tail" === n.pointsToMove || c) && (l[2] = a(e, o, l[2]), l[3] = a(t, i, l[3])), b[b.length - 1] = [s[0], l, s[2]]), "text" === s[0] && (console.log("p[0] === 'text'"), ("head" === n.pointsToMove || c) && (console.log("g.pointsToMove === 'head' || isMoveAllPoints"), l[1] = a(e, o, l[1]), l[2] = a(t, i, l[2])), b[b.length - 1] = [s[0], l, s[2]]), "arc" === s[0] && (l[0] = a(e, o, l[0]), l[1] = a(t, i, l[1]), b[b.length - 1] = [s[0], l, s[2]]), "rect" === s[0]) {
                    if (c && (l[0] = a(e, o, l[0]), l[1] = a(t, i, l[1])), "stretch-first" === n.pointsToMove) {
                        var p = r(e, t, o, i, {
                            x: l[0],
                            y: l[1],
                            width: l[2],
                            height: l[3],
                            pointsToMove: n.pointsToMove
                        });
                        l[0] = p.x, l[1] = p.y, l[2] = p.width, l[3] = p.height
                    }
                    if ("stretch-second" === n.pointsToMove) {
                        var p = r(e, t, o, i, {
                            x: l[0],
                            y: l[1],
                            width: l[2],
                            height: l[3],
                            pointsToMove: n.pointsToMove
                        });
                        l[1] = p.y, l[2] = p.width, l[3] = p.height
                    }
                    if ("stretch-third" === n.pointsToMove) {
                        var p = r(e, t, o, i, {
                            x: l[0],
                            y: l[1],
                            width: l[2],
                            height: l[3],
                            pointsToMove: n.pointsToMove
                        });
                        l[0] = p.x, l[2] = p.width, l[3] = p.height
                    }
                    "stretch-last" === n.pointsToMove && (l[2] = a(e, o, l[2]), l[3] = a(t, i, l[3])), b[b.length - 1] = [s[0], l, s[2]]
                }
                if ("image" === s[0]) {
                    if (c && (l[1] = a(e, o, l[1]), l[2] = a(t, i, l[2])), "stretch-first" === n.pointsToMove) {
                        var p = r(e, t, o, i, {
                            x: l[1],
                            y: l[2],
                            width: l[3],
                            height: l[4],
                            pointsToMove: n.pointsToMove
                        });
                        l[1] = p.x, l[2] = p.y, l[3] = p.width, l[4] = p.height
                    }
                    if ("stretch-second" === n.pointsToMove) {
                        var p = r(e, t, o, i, {
                            x: l[1],
                            y: l[2],
                            width: l[3],
                            height: l[4],
                            pointsToMove: n.pointsToMove
                        });
                        l[2] = p.y, l[3] = p.width, l[4] = p.height
                    }
                    if ("stretch-third" === n.pointsToMove) {
                        var p = r(e, t, o, i, {
                            x: l[1],
                            y: l[2],
                            width: l[3],
                            height: l[4],
                            pointsToMove: n.pointsToMove
                        });
                        l[1] = p.x, l[3] = p.width, l[4] = p.height
                    }
                    "stretch-last" === n.pointsToMove && (l[3] = a(e, o, l[3]), l[4] = a(t, i, l[4])), b[b.length - 1] = [s[0], l, s[2]]
                }
                "quadratic" === s[0] && (("starting-points" === n.pointsToMove || c) && (l[0] = a(e, o, l[0]), l[1] = a(t, i, l[1])), ("control-points" === n.pointsToMove || c) && (l[2] = a(e, o, l[2]), l[3] = a(t, i, l[3])), ("ending-points" === n.pointsToMove || c) && (l[4] = a(e, o, l[4]), l[5] = a(t, i, l[5])), b[b.length - 1] = [s[0], l, s[2]]), "bezier" === s[0] && (("starting-points" === n.pointsToMove || c) && (l[0] = a(e, o, l[0]), l[1] = a(t, i, l[1])), ("1st-control-points" === n.pointsToMove || c) && (l[2] = a(e, o, l[2]), l[3] = a(t, i, l[3])), ("2nd-control-points" === n.pointsToMove || c) && (l[4] = a(e, o, l[4]), l[5] = a(t, i, l[5])), ("ending-points" === n.pointsToMove || c) && (l[6] = a(e, o, l[6]), l[7] = a(t, i, l[7])), b[b.length - 1] = [s[0], l, s[2]])
            }
        },
        Q = {
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0, R.lineCap = "round", U.line(R, [o.prevX, o.prevY, t, n]), b[b.length] = ["line", [o.prevX, o.prevY, t, n], U.getOptions()], o.prevX = t, o.prevY = n
            },
            mouseup: function(e) {
                this.ismousedown = !1
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.lineCap = "round", U.line(R, [o.prevX, o.prevY, t, n]), b[b.length] = ["line", [o.prevX, o.prevY, t, n], U.getOptions()], o.prevX = t, o.prevY = n)
            }
        },
        N = document.getElementById("pencil-stroke-style").value,
        W = "#" + document.getElementById("pencil-fill-style").value,
        U = d(z);
    U.getOptions = function() {
        return [N, W, S, M, X, Y, L, E]
    };
    var q = {
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0, R.lineCap = "round", j.line(R, [o.prevX, o.prevY, t, n]), b[b.length] = ["line", [o.prevX, o.prevY, t, n], j.getOptions()], o.prevX = t, o.prevY = n
            },
            mouseup: function(e) {
                this.ismousedown = !1
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.lineCap = "round", j.line(R, [o.prevX, o.prevY, t, n]), b[b.length] = ["line", [o.prevX, o.prevY, t, n], j.getOptions()], o.prevX = t, o.prevY = n)
            }
        },
        G = document.getElementById("marker-stroke-style").value,
        V = "#" + document.getElementById("marker-fill-style").value,
        J = .7,
        j = d(z);
    j.getOptions = function() {
        return [G, V, S, J, X, Y, L, E]
    };
    var K = {
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0, R.lineCap = "round", z.line(R, [o.prevX, o.prevY, t, n]), b[b.length] = ["line", [o.prevX, o.prevY, t, n], z.getOptions()], o.prevX = t, o.prevY = n
            },
            mouseup: function(e) {
                this.ismousedown = !1
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.lineCap = "round", z.line(R, [o.prevX, o.prevY, t, n]), b[b.length] = ["line", [o.prevX, o.prevY, t, n], z.getOptions()], o.prevX = t, o.prevY = n)
            }
        },
        Z = {
            text: "",
            selectedFontFamily: "Arial",
            selectedFontSize: "15",
            onShapeSelected: function() {
                R.canvas.style.cursor = "text", this.x = this.y = this.pageX = this.pageY = 0, this.text = ""
            },
            onShapeUnSelected: function() {
                this.text = "", this.showOrHideTextTools("hide"), R.canvas.style.cursor = "default", "undefined" != typeof this.blinkCursorInterval && clearInterval(this.blinkCursorInterval)
            },
            getFillColor: function(e) {
                return e = (e || S).toLowerCase(), "rgba(255, 255, 255, 0)" == e || "transparent" == e || "white" === e ? "black" : e
            },
            writeText: function(e, t) {
                if (P.isText) {
                    if (t) return Z.text = Z.text.substr(0, Z.text.length - 1), void Z.fillText(Z.text);
                    Z.text += e, Z.fillText(Z.text)
                }
            },
            fillText: function(e) {
                if (P.isText) {
                    R.clearRect(0, 0, R.canvas.width, R.canvas.height);
                    var t = Z.getOptions();
                    z.handleOptions(R, t), R.fillStyle = Z.getFillColor(t[2]), R.font = Z.selectedFontSize + 'px "' + Z.selectedFontFamily + '"', R.fillText(e, Z.x, Z.y)
                }
            },
            blinkCursorInterval: null,
            index: 0,
            blinkCursor: function() {
                Z.index++, Z.index % 2 == 0 ? Z.fillText(Z.text + "|") : Z.fillText(Z.text)
            },
            getOptions: function() {
                var e = {
                    font: Z.selectedFontSize + 'px "' + Z.selectedFontFamily + '"',
                    fillStyle: Z.getFillColor(),
                    strokeStyle: "#6c96c8",
                    globalCompositeOperation: "source-over",
                    globalAlpha: 1,
                    lineJoin: "round",
                    lineCap: "round",
                    lineWidth: 2
                };
                return E = e.font, e
            },
            appendPoints: function() {
                console.log("appendPoints");
                var e = Z.getOptions();
                b[b.length] = ["text", ['"' + Z.text + '"', Z.x, Z.y], z.getOptions(e)]
            },
            mousedown: function(e) {
                console.log("mousedown"), console.log("mousedown Event @@@", e), P.isText && (Z.text.length && this.appendPoints(), Z.x = Z.y = 0, Z.text = "", Z.pageX = e.pageX, Z.pageY = e.pageY, Z.x = e.pageX - pe.offsetLeft - 5, Z.y = e.pageY - pe.offsetTop + 10, "undefined" != typeof Z.blinkCursorInterval && clearInterval(Z.blinkCursorInterval), Z.blinkCursor(), Z.blinkCursorInterval = setInterval(Z.blinkCursor, 700), this.showTextTools())
            },
            onTextBtnSend: function(e) {
                console.log("mousedown"), console.log("mousedown Event @@@", e), P.isText && (Z.text.length && Z.appendPoints(), Z.x = Z.y = 0, Z.text = "", Z.pageX = e.pageX, Z.pageY = e.pageY, Z.x = e.pageX - pe.offsetLeft - 5, Z.y = e.pageY - pe.offsetTop + 10, "undefined" != typeof Z.blinkCursorInterval && clearInterval(Z.blinkCursorInterval), Z.blinkCursor(), Z.blinkCursorInterval = setInterval(Z.blinkCursor, 700), Z.showTextTools())
            },
            mouseup: function(e) {},
            mousemove: function(e) {},
            showOrHideTextTools: function(e) {
                this.fontFamilyBox.style.display = "show" == e ? "block" : "none", this.fontSizeBox.style.display = "show" == e ? "block" : "none", this.fontSizeBox.style.left = this.x + "px", this.fontFamilyBox.style.left = this.fontSizeBox.clientWidth + this.x + "px", this.fontSizeBox.style.top = this.y + "px", this.fontFamilyBox.style.top = this.y + "px"
            },
            showTextTools: function() {
                this.fontFamilyBox && this.fontSizeBox && (this.unselectAllFontFamilies(), this.unselectAllFontSizes(), this.showOrHideTextTools("show"), this.eachFontFamily(function(e) {
                    e.onclick = function(t) {
                        console.log("Entered the onclick of send 11111", e.id), Z.showOrHideTextTools("hide"), C = !0, z.redraw()
                    }
                }), this.eachFontSize(function(e) {
                    e.onclick = function(e) {
                        e.preventDefault(), console.log("Entered the onclick of send 2222"), Z.showOrHideTextTools("hide"), Z.selectedFontSize = this.innerHTML, this.className = "font-family-selected"
                    }
                }))
            },
            eachFontFamily: function(e) {
                for (var t = this.fontFamilyBox.querySelectorAll("li"), n = 0; n < t.length; n++) e(t[n])
            },
            unselectAllFontFamilies: function() {
                this.eachFontFamily(function(e) {
                    e.className = "", e.innerHTML === Z.selectedFontFamily && (e.className = "font-family-selected")
                })
            },
            eachFontSize: function(e) {
                for (var t = this.fontSizeBox.querySelectorAll("li"), n = 0; n < t.length; n++) e(t[n])
            },
            unselectAllFontSizes: function() {
                this.eachFontSize(function(e) {
                    e.className = "", e.innerHTML === Z.selectedFontSize && (e.className = "font-size-selected")
                })
            },
            onReturnKeyPressed: function() {
                if (console.log("onReturnKeyPressed"), Z.text && Z.text.length) {
                    var e = parseInt(Z.selectedFontSize) || 15;
                    this.mousedown({
                        pageX: this.pageX,
                        pageY: this.pageY + e + 5
                    }), z.redraw()
                }
            },
            fontFamilyBox: document.querySelector(".fontSelectUl"),
            fontSizeBox: document.querySelector(".fontSizeUl")
        },
        $ = {
            global: {
                ismousedown: !1,
                prevX: 0,
                prevY: 0,
                prevRadius: 0,
                isCircleDrawn: !1,
                isCircledEnded: !0,
                isClockwise: !1,
                arcRangeContainer: null,
                arcRange: null
            },
            mousedown: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop;
                t.prevX = n, t.prevY = o, t.ismousedown = !0
            },
            mouseup: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop;
                if (t.ismousedown)
                    if (!t.isCircleDrawn && t.isCircledEnded) {
                        var i = t.prevX,
                            s = t.prevY,
                            l = (n - i + (o - s)) / 3;
                        t.prevRadius = l, t.isCircleDrawn = !0, t.isCircleEnded = !1;
                        var a, r = 2 * Math.PI * l / 21,
                            c = i > n ? i - n : n - i,
                            p = s > o ? s - o : o - s;
                        a = (c + p) / (2 * r), b[b.length] = ["arc", [i + l, s + l, l, a, 1], z.getOptions()];
                        var d = t.arcRange,
                            h = t.arcRangeContainer;
                        h.style.display = "block", d.focus(), h.style.top = o + pe.offsetTop + 20 + "px", h.style.left = n + "px", d.value = 2
                    } else t.isCircleDrawn && !t.isCircleEnded && this.end();
                t.ismousedown = !1, this.fixAllPoints()
            },
            mousemove: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop,
                    i = t.ismousedown,
                    s = t.isCircleDrawn,
                    l = t.isCircledEnded;
                if (i && !s && l) {
                    var a = t.prevX,
                        r = t.prevY,
                        c = (n - a + (o - r)) / 3;
                    R.clearRect(0, 0, 2e3, 2e3), z.arc(R, [a + c, r + c, c, 2 * Math.PI, !0])
                }
            },
            fixAllPoints: function() {
                for (var e = this.toFixed, t = 0; t < b.length; t++) {
                    var n, o = b[t];
                    "arc" === o[0] && (n = o[1], b[t] = ["arc", [e(n[0]), e(n[1]), e(n[2]), e(n[3]), n[4]], o[2]])
                }
            },
            init: function() {
                var n = t("is-clockwise"),
                    o = this.global;
                o.arcRangeContainer = t("arc-range-container"), o.arcRange = t("arc-range"), e(n, "change", function(e) {
                    if (o.isClockwise = n.checked, o.arcRange.value = $.toFixed(o.arcRange.value), o.arcRange.focus(), $.arcRangeHandler(e), b.length) {
                        var t = b[b.length - 1],
                            i = t[1];
                        R.clearRect(0, 0, innerWidth, innerHeight), z.arc(R, [i[0], i[1], i[2], i[3], i[4]])
                    }
                });
                var i = o.arcRange;
                e(i, "keydown", this.arcRangeHandler), e(i, "focus", this.arcRangeHandler)
            },
            arcRangeHandler: function(e) {
                var t = $.global,
                    n = t.arcRange,
                    o = e.keyCode,
                    i = +n.value;
                if ((39 == o || 40 == o) && (n.value = (2 > i ? i : 1.98) + .02), (37 == o || 38 == o) && (n.value = (i > 0 ? i : .02) - .02), !o || 13 == o || 39 == o || 40 == o || 37 == o || 38 == o) {
                    var s = Math.PI * $.toFixed(i),
                        l = b[b.length - 1];
                    if ("arc" === l[0]) {
                        var a = l[1];
                        b[b.length - 1] = ["arc", [a[0], a[1], a[2], s, t.isClockwise ? 1 : 0], l[2]], z.redraw()
                    }
                }
            },
            toFixed: function(e) {
                return Number(e).toFixed(1)
            },
            end: function() {
                var e = this.global;
                e.arcRangeContainer.style.display = "none", e.arcRange.value = 2, e.isCircleDrawn = !1, e.isCircleEnded = !0, z.redraw()
            }
        };
    $.init();
    var _ = {
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0
            },
            mouseup: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (b[b.length] = ["line", [o.prevX, o.prevY, t, n], z.getOptions()], o.ismousedown = !1)
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.clearRect(0, 0, innerWidth, innerHeight), z.line(R, [o.prevX, o.prevY, t, n]))
            }
        },
        ee = {
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            arrowSize: 10,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0
            },
            mouseup: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (b[b.length] = ["arrow", [o.prevX, o.prevY, t, n], z.getOptions()], o.ismousedown = !1)
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.clearRect(0, 0, innerWidth, innerHeight), z.arrow(R, [o.prevX, o.prevY, t, n]))
            }
        },
        te = {
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0
            },
            mouseup: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (b[b.length] = ["rect", [o.prevX, o.prevY, t - o.prevX, n - o.prevY], z.getOptions()], o.ismousedown = !1)
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.clearRect(0, 0, innerWidth, innerHeight), z.rect(R, [o.prevX, o.prevY, t - o.prevX, n - o.prevY]))
            }
        },
        ne = {
            global: {
                ismousedown: !1,
                prevX: 0,
                prevY: 0,
                controlPointX: 0,
                controlPointY: 0,
                isFirstStep: !0,
                isLastStep: !1
            },
            mousedown: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop;
                t.isLastStep || (t.prevX = n, t.prevY = o), t.ismousedown = !0, t.isLastStep && t.ismousedown && this.end(n, o)
            },
            mouseup: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop;
                t.ismousedown && t.isFirstStep && (t.controlPointX = n, t.controlPointY = o, t.isFirstStep = !1, t.isLastStep = !0)
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this.global;
                R.clearRect(0, 0, innerWidth, innerHeight), o.ismousedown && o.isFirstStep && z.quadratic(R, [o.prevX, o.prevY, t, n, t, n]), o.isLastStep && z.quadratic(R, [o.prevX, o.prevY, o.controlPointX, o.controlPointY, t, n])
            },
            end: function(e, t) {
                var n = this.global;
                n.ismousedown && (n.isLastStep = !1, n.isFirstStep = !0, n.ismousedown = !1, e = e || n.controlPointX || n.prevX, t = t || n.controlPointY || n.prevY, b[b.length] = ["quadratic", [n.prevX, n.prevY, n.controlPointX, n.controlPointY, e, t], z.getOptions()])
            }
        },
        oe = {
            global: {
                ismousedown: !1,
                prevX: 0,
                prevY: 0,
                firstControlPointX: 0,
                firstControlPointY: 0,
                secondControlPointX: 0,
                secondControlPointY: 0,
                isFirstStep: !0,
                isSecondStep: !1,
                isLastStep: !1
            },
            mousedown: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop;
                t.isLastStep || t.isSecondStep || (t.prevX = n, t.prevY = o), t.ismousedown = !0, t.isLastStep && t.ismousedown && this.end(n, o), t.ismousedown && t.isSecondStep && (t.secondControlPointX = n, t.secondControlPointY = o, t.isSecondStep = !1, t.isLastStep = !0)
            },
            mouseup: function(e) {
                var t = this.global,
                    n = e.pageX - pe.offsetLeft,
                    o = e.pageY - pe.offsetTop;
                t.ismousedown && t.isFirstStep && (t.firstControlPointX = n, t.firstControlPointY = o, t.isFirstStep = !1, t.isSecondStep = !0)
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this.global;
                R.clearRect(0, 0, innerWidth, innerHeight), o.ismousedown && o.isFirstStep && z.bezier(R, [o.prevX, o.prevY, t, n, t, n, t, n]), o.ismousedown && o.isSecondStep && z.bezier(R, [o.prevX, o.prevY, o.firstControlPointX, o.firstControlPointY, t, n, t, n]), o.isLastStep && z.bezier(R, [o.prevX, o.prevY, o.firstControlPointX, o.firstControlPointY, o.secondControlPointX, o.secondControlPointY, t, n])
            },
            end: function(e, t) {
                var n = this.global;
                n.ismousedown && (n.isLastStep = n.isSecondStep = !1, n.isFirstStep = !0, n.ismousedown = !1, n.secondControlPointX = n.secondControlPointX || n.firstControlPointX, n.secondControlPointY = n.secondControlPointY || n.firstControlPointY, e = e || n.secondControlPointX, t = t || n.secondControlPointY, b[b.length] = ["bezier", [n.prevX, n.prevY, n.firstControlPointX, n.firstControlPointY, n.secondControlPointX, n.secondControlPointY, e, t], z.getOptions()])
            }
        },
        ie = {
            scale: 1,
            up: function(e) {
                this.scale += .01, this.apply()
            },
            down: function(e) {
                this.scale -= .01, this.apply()
            },
            apply: function() {
                R.scale(this.scale, this.scale), D.scale(this.scale, this.scale), z.redraw()
            },
            icons: {
                up: function(e) {
                    var t = new Image;
                    t.width = 32, t.height = 32, t.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAZdEVYdFNvZnR3YXJlAHBhaW50Lm5ldCA0LjAuMTJDBGvsAAADiklEQVRoQ+2ZLXMUQRCGT0RERCCQiJMRyEjEiYiIiBOIiIiIiIiIExHIq4qlKiI/AMEPiIhEREREIpAIBAKBQCAQiON9trphcnt787F7lZoqRNfex0x3v909/TE7WiwWo5qpauUx/H8ATx1+g3hgNBptiw5F16Ib0YPoq0g+bp5853f+Z932UMCLAUiJLdGRKfbTlEXhFGI9gNi/1QdMEQAJPRB9DJT9rc/3oguz8J6eL5pDpqeI71ie/1nHegf6SZ+npSCyAEjQWHQXCP+sz6ei5zkKsF50JmK/A4Hvbg6frCwk5hPRNxP4w6zZK5bFgzCcib4HfPdzQCR5wKzsbv+g789yhMTWwk90ayCQcxbb4/9HAZjy7uYrrJbKPHedeF8GIXWasn8tAAsbt/x5CsO+ayTzOPDEJMavE4CYjIOYv4oxGvJ/yZ0bCM7ceB3vdQA82xDz2WETKDEvAaf91AlC9y4bgDaR59lMtik6sAMA2Aki4KALRMsDWFvkReqixHpWwDwMijxgPM7NkOizMgpWAaC8Y32KTHGe7+sBA4AxvdgdrTLmKgAee0lprNO1/w5isQcMBBUbg95EAWBxEY0WqTOrPVhmPoQHDABtB/qgVysiHnlAC2i4QHtfGvt/K+RAHjAQNIDoddgyVPiDFtCvs7D48G4IAF0sel3HAHj8t5DmemSoEDIPeGS0zsFyCDE5gXQvV+FNnQEDwDyBXg8xD/gY2AwjHUrNxOjEaKrnxOilnmMjitDchPbKQgaAoagZT2MAmq5zTWpMGReX15A9vhjhYVoU6L3onRFdKIA7wRqAlm7LIZTiAVpqF0wP7woxGrqiv1xg7rPD88ke2MQZeBuE1qsg5GibT4wa63d5QL8nn4Hqs1D1daD6Slx3L2Q5t95u1ACE80D2KDlkL6TsUzQPsImcTkGalbYUlhbhUVyJtZdbP3igT9pEZl6Y2kZuzJ5qJuY8+k1g591pyq3EbYkX+npA+2k1ym4lzAu7YsCtBEwuS0CU7pG8NyYX+WsvfGM3c/ti4Ddzx6UK5eyTvNcmE7nRi96Uu1EfqnsdyBQQZnk3WNIFbxSAhRPZwBlTJ3ZSFEpdI34cWI/5YW+ng7w+CbIC2YFLp+I6YYYhZWOc8L1DNGxCwyR5IAAxljD6fx9a6nlD8wh1re/IlkDU+5ZyxbBf33vi1Ayz6XVZh3jTypTwrx7AH8HANnr8oqz7AAAAAElFTkSuQmCC", e.drawImage(t, 4, 4, 32, 32)
                },
                down: function(e) {
                    var t = new Image;
                    t.width = 32, t.height = 32, t.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAZdEVYdFNvZnR3YXJlAHBhaW50Lm5ldCA0LjAuMTJDBGvsAAADdUlEQVRoQ+2Zr1IcQRDGTyAQiIiIiIiTiEhkxAkEAnEiAoGIQCAQJxCRVPEACB4AkQdAICMQCGREZERERERERERExOX7bXVXDXu7NzP7h6upylV17e5tT3d/3T09PbOT5XI5KZmKNh7H/wew6fQbJAKTyWRbdCi6Ft2KHkXfRYpxdeWZ/3kP3/ZQwDsDkBFboiMz7LcZi8EpBD+AGL/VB0wnAFJ6IPocGPtX9w+ic/Pwnq6vq0mmq4hnPM97+OB3oF90P+8KIguAFE1F94Hyr7o/Eb3MMQB+0amI8Q4Eubs5crKqkITPRD9M4S/zZq9clgzScCH6GcjdzwGRFAHzsof9k55f5CiJ8SJPdGcg0HMaG+PvowDMeA/zFV5LFZ7LJ9mXQUqdpIxfC8DSxj1/liKwL490HgeRmMXktQKQkGmQ81cxQUO+l94LA8Gcm66TvQ6AVxtyfrS0aTNOOlknSN37bAAaRJ1nMNVm0AmbGinp3Qky4KAVaP0F3hb5InWeqnAMPtlxZo7EnsYsWEkhMbK8430WmV51vi8oc6YvdkdN8poAeO4llbG+RsbGCwQrNg69jQLA4yIaLUpnVnsQM6Tre+wwe7BrJSOeREAMNFygfeiqcIxx2GN2Ha7M2fAPMdGvA2Cjk7ehsNDFYtd1DIDn/wrSMTybKjPIjJV5UE8hdk4g3UsV/hx82GN2PcYi4NvAajPSEMoLCVqI3hvNdZ0ZvdF1arQzJDDJZFNUbU9jAKqus3XVS9su1reU9DPfjIgwLQr0UXRjRBeKcy5iumMAUiJAS+2K6eHdILaGbugf81gdTPS5JfLJERh8DgjIqyC13gYpR9vsqVh5vy0C+j95DhRfhYpfB4pficvuhagA+pXbjRqAcD/w7FvJWm/G5ip7P8AgajrhWAy5oubKkn5O/bADe9J2ZBaFuQ3kxGxTe2Lmo58Etp6dppxK3OV6bgh+GU+r0e1UwqKwKwGcSiDkcgijUmVI3wfTi/61B76xk7l9CfCTueNUA/rwSd8704ne6EFvytmob6qJRGu32MdoH2ued4clHfBGAVg6UQ1cMOvE0P0+E9ZzftjT6cA7s6AqUB04dOq1TjDeSmX43SGaNk/WipzQS9lURP/vfX05X2hqK2SZ38galvkyv1I2bPbL+06cM3fG5E0qo2Ma0Fd28QD+AZqKxdXXwZ1FAAAAAElFTkSuQmCC", e.drawImage(t, 4, 4, 32, 32)
                }
            }
        },
        se = function() {
            function e(e, n) {
                var o = document.createElement("input");
                o.type = "file", n && (o.multiple = !0), o.accept = "image/*", o.onchange = function() {
                    return n ? o.files.length ? void e(o.files) : void console.error("No file selected.") : o.files[0] ? (e(o.files[0]), void o.parentNode.removeChild(o)) : void console.error("No file selected.")
                }, o.style.display = "none", (document.body || document.documentElement).appendChild(o), t(o)
            }

            function t(e) {
                var t = new window.MouseEvent("click", {
                    view: window,
                    bubbles: !0,
                    cancelable: !0,
                    button: 0,
                    buttons: 0,
                    mozInputSource: 1
                });
                e.dispatchEvent(t)
            }
            var n = this;
            n.selectSingleFile = e, n.selectMultipleFiles = function(t) {
                e(t, !0)
            }
        },
        le = {
            lastImageURL: null,
            lastImageIndex: 0,
            images: [],
            ismousedown: !1,
            prevX: 0,
            prevY: 0,
            mousedown: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.prevX = t, o.prevY = n, o.ismousedown = !0
            },
            mouseup: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (b[b.length] = ["image", [le.lastImageURL, o.prevX, o.prevY, t - o.prevX, n - o.prevY, le.lastImageIndex], z.getOptions()], o.ismousedown = !1)
            },
            mousemove: function(e) {
                var t = e.pageX - pe.offsetLeft,
                    n = e.pageY - pe.offsetTop,
                    o = this;
                o.ismousedown && (R.clearRect(0, 0, innerWidth, innerHeight), z.image(R, [le.lastImageURL, o.prevX, o.prevY, t - o.prevX, n - o.prevY, le.lastImageIndex]))
            }
        },
        ae = {
            line: !0,
            arrow: !0,
            pencil: !0,
            marker: !0,
            dragSingle: !0,
            dragMultiple: !0,
            eraser: !0,
            rectangle: !0,
            arc: !0,
            bezier: !0,
            quadratic: !0,
            text: !0,
            image: !0,
            zoom: !0
        };
    if (params.tools) try {
        var re = JSON.parse(params.tools);
        ae = re
    } catch (ce) {}
    P.set(window.selectedIcon), window.addEventListener("load", function() {
            for (var e, t = document.getElementById("tool-box"), n = t.getElementsByTagName("canvas"), o = window.selectedIcon.toLowerCase(), i = 0; i < n.length; i++) e || -1 === (n[i].id || "").indexOf(o) || (e = n[i]);
            e || (window.selectedIcon = "Pencil", e = document.getElementById("pencil-icon")), u(e, window.selectedIcon)
        }, !1),
        function() {
            function n(e) {
                var n = t(e).getContext("2d");
                return n.lineWidth = 2, n.strokeStyle = "#6c96c8", n
            }

            function o(n, o) {
                ("Pencil" === o || "Marker" === o) && (Y = L = "round"), e(n.canvas, "click", function() {
                    if (Z.text.length && Z.appendPoints(), "Text" === o ? Z.onShapeSelected() : Z.onShapeUnSelected(), ("Pencil" === o || "Marker" === o) && (Y = L = "round"), H.global.startingIndex = 0, u(this, o), "drag-last-path" === this.id ? (t("copy-last").checked = !0, t("copy-all").checked = !1) : "drag-all-paths" === this.id && (t("copy-all").checked = !0, t("copy-last").checked = !1), "image-icon" === this.id) {
                        var e = new se;
                        e.selectSingleFile(function(e) {
                            if (e) {
                                var t = new FileReader;
                                t.onload = function(e) {
                                    var t = new Image;
                                    t.onload = function() {
                                        var e = le.images.length;
                                        le.lastImageURL = t.src, le.lastImageIndex = e, le.images.push(t)
                                    }, t.src = e.target.result
                                }, t.readAsDataURL(e)
                            }
                        })
                    }
                    "pencil-icon" === this.id || "eraser-icon" === this.id || "marker-icon" === this.id ? (q.lineCap = Y, q.lineJoin = L, Y = L = "round") : q.lineCap && q.lineJoin && (Y = q.lineCap, L = q.lineJoin), "eraser-icon" === this.id ? (q.strokeStyle = I, q.fillStyle = S, q.lineWidth = F, I = "White", S = "White", F = 40) : q.strokeStyle && q.fillStyle && "undefined" != typeof q.lineWidth && (I = q.strokeStyle, S = q.fillStyle, F = q.lineWidth)
                })
            }

            function s() {
                var e, t, i = n("drag-last-path"),
                    s = 10,
                    l = 6,
                    a = "line",
                    r = [
                        [a, s, l, s + 5, l + 27],
                        [a, s, l, s + 18, l + 19],
                        [a, s + 17, l + 19, s + 9, l + 20],
                        [a, s + 9, l + 20, s + 5, l + 27],
                        [a, s + 16, l + 22, s + 16, l + 31],
                        [a, s + 12, l + 27, s + 20, l + 27]
                    ],
                    c = r.length;
                for (t = 0; c > t; t++) e = r[t], "line" === e[0] && (i.beginPath(), i.moveTo(e[1], e[2]), i.lineTo(e[3], e[4]), i.closePath(), i.stroke());
                i.fillStyle = "Gray", i.font = "9px Verdana", i.fillText("Last", 18, 12), o(i, "DragLastPath")
            }

            function l() {
                var e, t, i = n("drag-all-paths"),
                    s = 10,
                    l = 6,
                    a = "line",
                    r = [
                        [a, s, l, s + 5, l + 27],
                        [a, s, l, s + 18, l + 19],
                        [a, s + 17, l + 19, s + 9, l + 20],
                        [a, s + 9, l + 20, s + 5, l + 27],
                        [a, s + 16, l + 22, s + 16, l + 31],
                        [a, s + 12, l + 27, s + 20, l + 27]
                    ],
                    c = r.length;
                for (t = 0; c > t; t++) e = r[t], "line" === e[0] && (i.beginPath(), i.moveTo(e[1], e[2]), i.lineTo(e[3], e[4]), i.closePath(), i.stroke());
                i.fillStyle = "Gray", i.font = "10px Verdana", i.fillText("All", 20, 12), o(i, "DragAllPaths")
            }

            function a() {
                var e = n("line");
                e.moveTo(10, 15), e.lineTo(30, 35), e.stroke(), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("Line", 16, 12), o(e, "Line")
            }

            function r() {
                var e = n("arrow"),
                    t = 10,
                    i = 35;
                e.beginPath(), e.moveTo(t, i), e.lineTo(t + 20, i - 20), e.stroke(), e.beginPath(), e.moveTo(t + 15, i - 5), e.lineTo(t + 20, i - 20), e.stroke(), e.beginPath(), e.moveTo(t + 5, i - 15), e.lineTo(t + 20, i - 20), e.stroke(), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("Arrow", 5, 12), o(e, "Arrow")
            }

            function c() {
                var t = n("zoom-up");
                ie.icons.up(t), e(t.canvas, "click", function() {
                    ie.up()
                })
            }

            function p() {
                var t = n("zoom-down");
                ie.icons.down(t), e(t.canvas, "click", function() {
                    ie.down()
                })
            }

            function d() {
                function i(e, t) {
                    return "rgba(" + h(e).join(",") + ",1)"
                }
                var s = [
                        ["FFFFFF", "006600", "000099", "CC0000", "8C4600"],
                        ["CCCCCC", "00CC00", "6633CC", "FF0000", "B28500"],
                        ["666666", "66FFB2", "006DD9", "FF7373", "FF9933"],
                        ["333333", "26FF26", "6699FF", "CC33FF", "FFCC99"],
                        ["000000", "CCFF99", "BFDFFF", "FFBFBF", "FFFF33"]
                    ],
                    l = n("pencil-icon");
                l.lineWidth = 5, l.lineCap = "round", l.moveTo(35, 20), l.lineTo(5, 35), l.stroke(), l.fillStyle = "Gray", l.font = "9px Verdana", l.fillText("Pencil", 6, 12), o(l, "Pencil");
                var a = t("pencil-container"),
                    r = t("pencil-fill-colors"),
                    c = t("pencil-stroke-style"),
                    p = t("pencil-colors-list"),
                    d = t("pencil-fill-style"),
                    u = t("pencil-selected-color"),
                    g = t("pencil-selected-color-2"),
                    v = t("pencil-done"),
                    m = l.canvas,
                    y = .2;
                W = i(d.value, y), u.style.backgroundColor = g.style.backgroundColor = "#" + d.value, s.forEach(function(e) {
                    var t = "<tr>";
                    e.forEach(function(e) {
                        t += '<td style="background-color:#' + e + '" data-color="' + e + '"></td>'
                    }), t += "</tr>", p.innerHTML += t
                }), Array.prototype.slice.call(p.getElementsByTagName("td")).forEach(function(t) {
                    e(t, "mouseover", function() {
                        var e = t.getAttribute("data-color");
                        g.style.backgroundColor = "#" + e, d.value = e
                    }), e(t, "click", function() {
                        var e = t.getAttribute("data-color");
                        u.style.backgroundColor = g.style.backgroundColor = "#" + e, d.value = e, r.style.display = "none"
                    })
                }), e(m, "click", function() {
                    f(), a.style.display = "block", a.style.top = m.offsetTop + 1 + "px", a.style.left = m.offsetLeft + m.clientWidth + "px", d.focus()
                }), e(v, "click", function() {
                    a.style.display = "none", r.style.display = "none", N = c.value, W = i(d.value, y)
                }), e(u, "click", function() {
                    r.style.display = "block"
                })
            }

            function g() {
                function i(e, t) {
                    return "rgba(" + h(e).join(",") + "," + t + ")"
                }
                var s = [
                        ["FFFFFF", "006600", "000099", "CC0000", "8C4600"],
                        ["CCCCCC", "00CC00", "6633CC", "FF0000", "B28500"],
                        ["666666", "66FFB2", "006DD9", "FF7373", "FF9933"],
                        ["333333", "26FF26", "6699FF", "CC33FF", "FFCC99"],
                        ["000000", "CCFF99", "BFDFFF", "FFBFBF", "FFFF33"]
                    ],
                    l = n("marker-icon");
                l.lineWidth = 9, l.lineCap = "round", l.strokeStyle = "green", l.moveTo(35, 20), l.lineTo(5, 25), l.stroke(), l.fillStyle = "Gray", l.font = "9px Verdana", l.fillText("Marker", 6, 12), o(l, "Marker");
                var a = t("marker-container"),
                    r = t("marker-fill-colors"),
                    c = t("marker-stroke-style"),
                    p = t("marker-colors-list"),
                    d = t("marker-fill-style"),
                    u = t("marker-selected-color"),
                    g = t("marker-selected-color-2"),
                    v = t("marker-done"),
                    m = l.canvas,
                    y = .2;
                V = i(d.value, y), u.style.backgroundColor = g.style.backgroundColor = "#" + d.value, s.forEach(function(e) {
                    var t = "<tr>";
                    e.forEach(function(e) {
                        t += '<td style="background-color:#' + e + '" data-color="' + e + '"></td>'
                    }), t += "</tr>", p.innerHTML += t
                }), Array.prototype.slice.call(p.getElementsByTagName("td")).forEach(function(t) {
                    e(t, "mouseover", function() {
                        var e = t.getAttribute("data-color");
                        g.style.backgroundColor = "#" + e, d.value = e
                    }), e(t, "click", function() {
                        var e = t.getAttribute("data-color");
                        u.style.backgroundColor = g.style.backgroundColor = "#" + e, d.value = e, r.style.display = "none"
                    })
                }), e(m, "click", function() {
                    f(), a.style.display = "block", a.style.top = m.offsetTop + 1 + "px", a.style.left = m.offsetLeft + m.clientWidth + "px", d.focus()
                }), e(v, "click", function() {
                    a.style.display = "none", r.style.display = "none", G = c.value, V = i(d.value, y)
                }), e(u, "click", function() {
                    r.style.display = "block"
                })
            }

            function v() {
                var e = n("eraser-icon");
                e.lineWidth = 9, e.lineCap = "round", e.moveTo(35, 20), e.lineTo(5, 25), e.stroke(), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("Eraser", 6, 12), o(e, "Eraser")
            }

            function m() {
                var e = n("text-icon");
                e.font = "22px Verdana", e.strokeText("T", 15, 30), o(e, "Text")
            }

            function y() {
                var e = n("image-icon"),
                    t = new Image;
                t.onload = function() {
                    e.drawImage(t, 4, 4, 32, 32), o(e, "Image")
                }, t.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAMAAABEpIrGAAAADFBMVEVYWFhVVVUAAABUVFTqqlXjAAAAA3RSTlMxdACUjPeLAAAATElEQVR42u3SQQrAMAwDQSn7/z+XFExTcOxroN3zgC4STecApy1gpP2gBgZXQMwKwJ23QITYACLlQBC9gAFNwJMXoJhVc7lBA/gsuAArEgqPcT12VgAAAABJRU5ErkJggg=="
            }

            function x() {
                var e = n("arc");
                e.arc(20, 20, 16.3, 2 * Math.PI, 0, 1), e.stroke(), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("Arc", 10, 24), o(e, "Arc")
            }

            function w() {
                var e = n("rectangle");
                e.strokeRect(5, 5, 30, 30), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("Rect", 8, 24), o(e, "Rectangle")
            }

            function A() {
                var e = n("quadratic-curve");
                e.moveTo(0, 0), e.quadraticCurveTo(50, 10, 30, 40), e.stroke(), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("quad..", 2, 24), o(e, "QuadraticCurve")
            }

            function b() {
                var e = n("bezier-curve"),
                    t = 0,
                    i = 4;
                e.moveTo(t, i), e.bezierCurveTo(t + 86, i + 16, t - 45, i + 24, t + 48, i + 34), e.stroke(), e.fillStyle = "Gray", e.font = "9px Verdana", e.fillText("Bezier", 10, 8), o(e, "BezierCurve")
            }

            function k(e, t, n, o, i, s) {
                e.beginPath(), e.lineWidth = t, e.moveTo(n, o), e.lineTo(i, s), e.stroke()
            }

            function E() {
                var o = n("line-width");
                k(o, 2, 5, 15, 35, 15), k(o, 3, 5, 20, 35, 20), k(o, 4, 5, 26, 35, 26), o.fillStyle = "Gray", o.font = "9px Verdana", o.fillText("Line", 8, 12), o.fillText("Width", 6, 38);
                var i = t("line-width-container"),
                    s = t("line-width-text"),
                    l = t("line-width-done"),
                    a = (document.getElementsByTagName("h1")[0], o.canvas);
                e(a, "click", function() {
                    f(), i.style.display = "block", i.style.top = a.offsetTop + 1 + "px", i.style.left = a.offsetLeft + a.clientWidth + "px", s.focus()
                }), e(l, "click", function() {
                    i.style.display = "none", F = s.value
                })
            }

            function D() {
                var o = n("colors");
                o.fillStyle = "red", o.fillRect(5, 3, 30, 10), o.fillStyle = "green", o.fillRect(5, 15, 30, 10), o.fillStyle = "blue", o.fillRect(5, 27, 30, 10);
                var i = t("colors-container"),
                    s = t("stroke-style"),
                    l = t("fill-style"),
                    a = t("colors-done"),
                    r = (document.getElementsByTagName("h1")[0], o.canvas);
                e(r, "click", function() {
                    f(), i.style.display = "block", i.style.top = r.offsetTop + 1 + "px", i.style.left = r.offsetLeft + r.clientWidth + "px", s.focus()
                }), e(a, "click", function() {
                    i.style.display = "none", I = s.value, S = l.value
                })
            }

            function R() {
                var o = n("additional");
                o.fillStyle = "#6c96c8", o.font = "35px Verdana", o.fillText("»", 10, 27), o.fillStyle = "Gray", o.font = "9px Verdana", o.fillText("Extras!", 2, 38);
                var i = t("additional-container"),
                    s = t("additional-close"),
                    l = (document.getElementsByTagName("h1")[0], o.canvas),
                    a = t("globalAlpha-select"),
                    r = t("globalCompositeOperation-select");
                e(l, "click", function() {
                    f(), i.style.display = "block", i.style.top = l.offsetTop + 1 + "px", i.style.left = l.offsetLeft + l.clientWidth + "px"
                }), e(s, "click", function() {
                    i.style.display = "none", M = a.value, X = r.value, Y = J.value, L = j.value
                })
            }

            function O() {
                te.parentNode.style.display = "none", ne.style.display = "none", f(), i()
            }

            function Q() {
                te.parentNode.style.display = "block", ne.style.display = "block", te.focus(), B.updateTextArea(), U(), f(), i()
            }

            function U() {
                te.style.width = innerWidth - ne.clientWidth - 30 + "px", te.style.height = innerHeight - 40 + "px", te.style.marginLeft = ne.clientWidth + "px", ne.style.height = innerHeight + "px"
            }
            var q = {},
                J = t("lineCap-select"),
                j = t("lineJoin-select"),
                K = t("tool-box");
            K.style.height = innerHeight + "px", ae.dragSingle === !0 ? s() : document.getElementById("drag-last-path").style.display = "none", ae.dragMultiple === !0 ? l() : document.getElementById("drag-all-paths").style.display = "none", ae.line === !0 ? a() : document.getElementById("line").style.display = "none", ae.arrow === !0 ? r() : document.getElementById("arrow").style.display = "none", ae.zoom === !0 ? (c(), p()) : (document.getElementById("zoom-up").style.display = "none", document.getElementById("zoom-down").style.display = "none"), ae.pencil === !0 ? d() : document.getElementById("pencil-icon").style.display = "none", ae.marker === !0 ? g() : document.getElementById("marker-icon").style.display = "none", ae.eraser === !0 ? v() : document.getElementById("eraser-icon").style.display = "none", ae.text === !0 ? m() : document.getElementById("text-icon").style.display = "none", ae.image === !0 ? y() : document.getElementById("image-icon").style.display = "none", ae.arc === !0 ? x() : document.getElementById("arc").style.display = "none", ae.rectangle === !0 ? w() : document.getElementById("rectangle").style.display = "none", ae.quadratic === !0 ? A() : document.getElementById("quadratic-curve").style.display = "none", ae.bezier === !0 ? b() : document.getElementById("bezier-curve").style.display = "none", E(), D(), R();
            var $ = t("design-preview"),
                _ = t("code-preview"),
                ee = t("sendbtntxt");
            window.selectBtn = function(e, t) {
                _.className = $.className = "", e == $ ? $.className = "preview-selected" : _.className = "preview-selected", !t && window.connection && connection.numberOfConnectedUsers >= 1 ? connection.send({
                    btnSelected: e.id
                }) : e == $ ? O() : Q()
            }, e($, "click", function() {
                selectBtn($), O()
            }), e(ee, "click", function(e) {
                var t = P;
                console.log("Inside addEvent &&^**^&*&&&", t.isText), console.log("selectedFontSize value", Z.selectedFontSize), console.log("text handler isTextSend value", C), Z.onTextBtnSend(e), z.redraw(), T(P.isDragAllPaths || P.isDragLastPath ? !0 : !1), e.preventDefault(), e.stopPropagation()
            });
            var te = t("code-text"),
                ne = t("options-container"),
                oe = t("is-absolute-points"),
                re = t("is-shorten-code");
            e(re, "change", B.updateTextArea), e(oe, "change", B.updateTextArea)
        }();
    var pe = R.canvas,
        de = "createTouch" in document;
    e(pe, de ? "touchstart" : "mousedown", function(e) {
        de && (e = e.pageX ? e : e.touches.length ? e.touches[0] : {
            pageX: 0,
            pageY: 0
        }), console.log("canvas ele " + pe);
        var t = P;
        console.log("Inside addEvent 1", t.isText), console.log("selectedFontSize value", Z.selectedFontSize), console.log("text handler isTextSend value", C), t.isLine ? _.mousedown(e) : t.isArc ? $.mousedown(e) : t.isRectangle ? te.mousedown(e) : t.isQuadraticCurve ? ne.mousedown(e) : t.isBezierCurve ? oe.mousedown(e) : t.isDragLastPath || t.isDragAllPaths ? H.mousedown(e) : t.isPencil ? Q.mousedown(e) : t.isEraser ? K.mousedown(e) : t.isText ? Z.mousedown(e) : t.isImage ? le.mousedown(e) : t.isArrow ? ee.mousedown(e) : t.isMarker ? q.mousedown(e) : C && Z.mousedown(e), z.redraw(), e.preventDefault(), e.stopPropagation()
    }), e(pe, de ? "touchend touchcancel" : "mouseup", function(e) {
        de && (e = e.pageX ? e : e.touches.length ? e.touches[0] : {
            pageX: 0,
            pageY: 0
        }), console.log("canvas ele " + pe);
        var t = P;
        console.log("Inside addEvent 2", t.isText), t.isLine ? _.mouseup(e) : t.isArc ? $.mouseup(e) : t.isRectangle ? te.mouseup(e) : t.isQuadraticCurve ? ne.mouseup(e) : t.isBezierCurve ? oe.mouseup(e) : t.isDragLastPath || t.isDragAllPaths ? H.mouseup(e) : t.isPencil ? Q.mouseup(e) : t.isEraser ? K.mouseup(e) : t.isText ? Z.mouseup(e) : t.isImage ? le.mouseup(e) : t.isArrow ? ee.mouseup(e) : t.isMarker && q.mouseup(e), z.redraw(), T(P.isDragAllPaths || P.isDragLastPath ? !0 : !1), e.preventDefault(), e.stopPropagation()
    }), e(pe, de ? "touchmove" : "mousemove", function(e) {
        de && (e = e.pageX ? e : e.touches.length ? e.touches[0] : {
            pageX: 0,
            pageY: 0
        });
        var t = P;
        console.log("Inside addEvent 3", t.isText), t.isLine ? _.mousemove(e) : t.isArc ? $.mousemove(e) : t.isRectangle ? te.mousemove(e) : t.isQuadraticCurve ? ne.mousemove(e) : t.isBezierCurve ? oe.mousemove(e) : t.isDragLastPath || t.isDragAllPaths ? H.mousemove(e) : t.isPencil ? Q.mousemove(e) : t.isEraser ? K.mousemove(e) : t.isText ? Z.mousemove(e) : t.isImage ? le.mousemove(e) : t.isArrow ? ee.mousemove(e) : t.isMarker && q.mousemove(e), e.preventDefault(), e.stopPropagation()
    });
    var he;
    e(document, "keydown", g), e(document, "keyup", m), e(document, "keypress", y), e(document, "paste", x);
    var ue, fe = 0;
    window.addEventListener("message", function(e) {
        if (console.log("event listener"), console.log("Event : ", e), console.log("Event : ", e.data), console.log("Event : ", e.data.uid), e.data) {
            if (ue || (ue = e.data.uid), e.data.genDataURL) {
                var t = D.canvas.toDataURL(e.data.format, 1);
                return void window.parent.postMessage({
                    dataURL: t,
                    uid: ue
                }, "*")
            }
            if (e.data.undo && b.length) {
                var n = e.data.index;
                if ("all" === n) return b = [], z.redraw(), void T(!0);
                if (n.numberOfLastShapes) {
                    try {
                        b.length -= n.numberOfLastShapes
                    } catch (o) {
                        b = []
                    }
                    return z.redraw(), void T(!0)
                }
                if (-1 === n) return b.length = b.length - 1, z.redraw(), void T(!0);
                if (b[n]) {
                    for (var i = [], s = 0; s < b.length; s++) s !== n && i.push(b[s]);
                    b = i, z.redraw(), T(!0)
                }
            } else {
                if (e.data.syncPoints) return void T(!0);
                if (e.data.canvasDesignerSyncData) {
                    var l = e.data.canvasDesignerSyncData;
                    if (0 !== l.startIndex)
                        for (var s = 0; s < l.points.length; s++) b[s + l.startIndex] = l.points[s];
                    else b = l.points;
                    fe = b.length, z.redraw()
                }
            }
        }
    }, !1)
}();