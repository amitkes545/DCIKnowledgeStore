! function() {
    function e(e, t) {
        function i() {
            a.signal({
                leaving: !0
            }), a.isbroadcaster && (a.stopBroadcasting = !0), e.stream && e.stream.stop(), window.Firebase && o.remove()
        }
        var o, r = e.userid || n();
        e.userid || (e.userid = r);
        var a = this,
            d = {},
            s = {},
            c = 0;
        this.onmessage = function(n) {
            if (n.roomid == t && n.broadcasting && !a.sentParticipationRequest ? e.onscreen(n) : console.debug(JSON.stringify(n, function(e, n) {
                    return n.sdp ? (console.log(n.sdp.type, "————", n.sdp.sdp), "") : n
                }, "————")), n.sdp && n.to == r && this.onsdp(n), n.candidate && n.to == r && this.onice(n), n.participationRequest && n.to == r) {
                var i = l;
                i.to = n.userid, i.stream = e.stream, d[n.userid] = h.createOffer(i), c++, e.onNumberOfParticipantsChnaged && e.onNumberOfParticipantsChnaged(c)
            }
        }, this.onsdp = function(n) {
            var t = JSON.parse(n.sdp);
            if ("offer" == t.type) {
                var i = l;
                i.stream = e.stream, i.sdp = t, i.to = n.userid, d[n.userid] = v.createAnswer(i)
            }
            "answer" == t.type && d[n.userid].setRemoteDescription(t)
        }, this.onice = function(e) {
            e.candidate = JSON.parse(e.candidate);
            var n = d[e.userid];
            if (n) {
                n.addIceCandidate(e.candidate);
                var t = s[e.userid] || [];
                if (t.length) {
                    for (var i = 0; i < t.length; i++) n.addIceCandidate(t[i]);
                    s[e.userid] = []
                }
            } else {
                var o = s[e.userid];
                o ? s[e.userid][o.length] = e.candidate : s[e.userid] = [e.candidate]
            }
        };
        var l = {
            onsdp: function(e, n) {
                console.log("local-sdp", JSON.stringify(e.sdp, null, "	")), a.signal({
                    sdp: JSON.stringify(e),
                    to: n
                })
            },
            onicecandidate: function(e, n) {
                a.signal({
                    candidate: JSON.stringify(e),
                    to: n
                })
            },
            onaddstream: function(n, t) {
                function i() {
                    return m ? o() : void(r.readyState <= HTMLMediaElement.HAVE_CURRENT_DATA || r.paused || r.currentTime <= 0 ? setTimeout(i, 300) : o())
                }

                function o() {
                    e.onaddstream && e.onaddstream({
                        video: r,
                        stream: n,
                        userid: t,
                        type: "remote"
                    })
                }
                console.debug("onaddstream", ">>>>>>", n), n.onended = function() {
                    e.onuserleft && e.onuserleft(t)
                };
                var r = document.createElement("video");
                r.id = t, r[u ? "mozSrcObject" : "src"] = u ? n : window.webkitURL.createObjectURL(n), r.autoplay = !0, r.controls = !0, r.play(), i()
            }
        };
        if (this.broadcast = function(t) {
                a.roomid = t.roomid || n(), t.userid && (r = t.userid), a.isbroadcaster = !0,
                    function i() {
                        a.signal({
                            roomid: a.roomid,
                            broadcasting: !0
                        }), a.stopBroadcasting || e.transmitOnce || setTimeout(i, 3e3)
                    }(), o.onDisconnect && o.onDisconnect().remove()
            }, this.join = function(e) {
                a.roomid = e.roomid, this.signal({
                    participationRequest: !0,
                    to: e.to
                }), a.sentParticipationRequest = !0
            }, window.addEventListener("beforeunload", function() {
                i()
            }, !1), window.addEventListener("keyup", function(e) {
                116 == e.keyCode && i()
            }, !1), e.leave = i, e.openSignalingChannel) o = e.openSignalingChannel(function(n) {
            n = JSON.parse(n);
            var t = !1;
            "number" == typeof r && parseInt(n.userid) != r && (t = !0), "string" == typeof r && n.userid + "" != r && (t = !0), t && (n.to && ("number" == typeof r && (n.to = parseInt(n.to)), "string" == typeof r && (n.to = n.to + "")), n.leaving ? (e.onuserleft(n.userid), c--, e.onNumberOfParticipantsChnaged && e.onNumberOfParticipantsChnaged(c)) : a.onmessage(n))
        }), this.signal = function(e) {
            e.userid = r, o.send(JSON.stringify(e))
        };
        else {
            if (!window.Firebase) throw "You must link <https://cdn.firebase.com/v0/firebase.js> file.";
            o = new window.Firebase("https://" + (e.firebase || "signaling") + ".firebaseIO.com/" + e.channel), o.on("child_added", function(n) {
                var t = n.val(),
                    i = !1;
                "number" == typeof r && parseInt(t.userid) != r && (i = !0), "string" == typeof r && t.userid + "" != r && (i = !0), i && (t.to && ("number" == typeof r && (t.to = parseInt(t.to)), "string" == typeof r && (t.to = t.to + "")), t.leaving ? (c--, e.onNumberOfParticipantsChnaged && e.onNumberOfParticipantsChnaged(c), e.onuserleft(t.userid)) : a.onmessage(t)), i && n.ref().remove()
            }), this.signal = function(e) {
                e.userid = r, o.push(e)
            }
        }
    }

    function n() {
        return Math.round(9999999999 * Math.random()) + 9999999999
    }

    function t() {}

    function i(e) {
        console.error("sdp error:", e)
    }

    function o(e) {
        if (u) return e;
        if (m) return e;
        if ("undefined" != typeof BandwidthHandler) {
            window.isMobileDevice = m, window.isFirefox = u;
            var n = {
                    screen: 300,
                    video: 256
                },
                t = !1;
            return e = BandwidthHandler.setApplicationSpecificBandwidth(e, n, t), e = BandwidthHandler.setVideoBitrates(e, {
                min: n.video,
                max: n.video
            })
        }
        return e = e.replace(/b=AS([^\r\n]+\r\n)/g, ""), e = e.replace(/a=mid:video\r\n/g, "a=mid:video\r\nb=AS:300\r\n")
    }

    function r(e, n) {
        var t = document.createElement("script");
        t.src = e, t.async = !0, document.documentElement.appendChild(t), console.log("loaded", e)
    }
    window.IsAndroidChrome = !1;
    try {
        navigator.userAgent.toLowerCase().indexOf("android") > -1 && /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor) && (window.IsAndroidChrome = !0)
    } catch (a) {}
    window.Screen = function(n) {
        function t(n) {
            o = new e(r, n && n.length || r.channel)
        }

        function i(e, n) {
            getScreenId(function(n, t, i) {
                IsAndroidChrome && (i = {
                    mandatory: {
                        chromeMediaSource: "screen"
                    },
                    optional: []
                }, i = {
                    video: i
                }, n = null), console.log("screen_constraintsssss", JSON.stringify(i, null, "	")), navigator.getUserMedia(i, function(n) {
                    n.onended = function() {
                        r.onuserleft && r.onuserleft("self")
                    }, r.stream = n;
                    var t = document.createElement("video");
                    t.id = "self", t[u ? "mozSrcObject" : "src"] = u ? n : window.webkitURL.createObjectURL(n), t.autoplay = !0, t.controls = !0, t.play(), r.onaddstream({
                        video: t,
                        stream: n,
                        userid: "self",
                        type: "local"
                    }), e(n)
                }, function(e) {
                    l && "http:" === location.protocol ? alert("You're not testing it on SSL origin (HTTPS domain) otherwise you didn't enable --allow-http-screen-capture command-line flag on canary.") : l ? alert("Screen capturing is either denied or not supported. Please install chrome extension for screen capturing or run chrome with command-line flag: --enable-usermedia-screen-capturing") : u && alert(a), console.error(e)
                })
            })
        }
        var o, r = this;
        this.channel = n || location.href.replace(/\/|:|#|%|\.|\[|\]/g, ""), this.onscreen = function(e) {
            r.detectedRoom || (r.detectedRoom = !0, r.view(e))
        };
        var a = 'Make sure that you are using Firefox Nightly and you enabled: media.getusermedia.screensharing.enabled flag from about:config page. You also need to add your domain in "media.getusermedia.screensharing.allowed_domains" flag.';
        this.share = function(e) {
            i(function() {
                !o && t(e), o.broadcast({
                    roomid: e && e.length || r.channel,
                    userid: r.userid
                })
            })
        }, this.view = function(e) {
            !o && t(), o.join({
                to: e.userid,
                roomid: e.roomid
            })
        }, this.check = t
    };
    var d = window.mozRTCPeerConnection || window.webkitRTCPeerConnection,
        s = window.mozRTCSessionDescription || window.RTCSessionDescription,
        c = window.mozRTCIceCandidate || window.RTCIceCandidate;
    navigator.getUserMedia = navigator.mozGetUserMedia || navigator.webkitGetUserMedia, window.URL = window.webkitURL || window.URL;
    var u = !!navigator.mozGetUserMedia,
        l = !!navigator.webkitGetUserMedia,
        m = !!navigator.userAgent.match(/Android|iPhone|iPad|iPod|BlackBerry|IEMobile/i),
        f = [];
    f.push({
        urls: "stun:stun.l.google.com:19302"
    }), f.push({
        urls: "turn:webrtcweb.com:80",
        credential: "muazkh",
        username: "muazkh"
    }), f.push({
        urls: "turn:webrtcweb.com:443",
        credential: "muazkh",
        username: "muazkh"
    }), f.push({
        urls: "turn:webrtcweb.com:3344",
        credential: "muazkh",
        username: "muazkh"
    }), f.push({
        urls: "turn:webrtcweb.com:4433",
        credential: "muazkh",
        username: "muazkh"
    }), f.push({
        urls: "turn:webrtcweb.com:4455",
        credential: "muazkh",
        username: "muazkh"
    }), f.push({
        urls: "turn:webrtcweb.com:5544?transport=tcp",
        credential: "muazkh",
        username: "muazkh"
    }), f = {
        iceServers: f
    };
    var p = {
            optional: [{
                DtlsSrtpKeyAgreement: !0
            }]
        },
        g = {
            optional: [],
            mandatory: {
                OfferToReceiveAudio: !1,
                OfferToReceiveVideo: !1
            }
        },
        h = {
            createOffer: function(e) {
                var n = new d(f, p);
                return n.addStream(e.stream), n.onicecandidate = function(n) {
                    n.candidate && e.onicecandidate(n.candidate, e.to)
                }, n.createOffer(function(t) {
                    t.sdp = o(t.sdp), n.setLocalDescription(t), e.onsdp(t, e.to)
                }, i, g), this.peer = n, this
            },
            setRemoteDescription: function(e) {
                console.log("setting remote descriptions", e.sdp), this.peer.setRemoteDescription(new s(e), t, i)
            },
            addIceCandidate: function(e) {
                console.log("adding ice", e.candidate), this.peer.addIceCandidate(new c({
                    sdpMLineIndex: e.sdpMLineIndex,
                    candidate: e.candidate
                }))
            }
        },
        w = {
            optional: [],
            mandatory: {
                OfferToReceiveAudio: !1,
                OfferToReceiveVideo: !0
            }
        },
        v = {
            createAnswer: function(e) {
                var n = new d(f, p);
                return n.onaddstream = function(n) {
                    e.onaddstream(n.stream, e.to)
                }, n.onicecandidate = function(n) {
                    n.candidate && e.onicecandidate(n.candidate, e.to)
                }, console.log("setting remote descriptions", e.sdp.sdp), n.setRemoteDescription(new s(e.sdp), t, i), n.createAnswer(function(t) {
                    t.sdp = o(t.sdp), n.setLocalDescription(t), e.onsdp(t, e.to)
                }, i, w), this.peer = n, this
            },
            addIceCandidate: function(e) {
                console.log("adding ice", e.candidate), this.peer.addIceCandidate(new c({
                    sdpMLineIndex: e.sdpMLineIndex,
                    candidate: e.candidate
                }))
            }
        };
    !window.getScreenId && r('src="../JS/getScreenId.js">')
}();