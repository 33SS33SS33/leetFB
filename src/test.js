var userAgent = navigator.userAgent,
  rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
  rFirefox = /(firefox)\/([\w.]+)/,
  rOpera = /(opera).+version\/([\w.]+)/,
  rChrome = /(chrome)\/([\w.]+)/,
  rSafari = /version\/([\w.]+).*(safari)/;
var browser;
var version;
var ua = userAgent.toLowerCase();

function uaMatch(ua) {
  var match = rMsie.exec(ua);
  if (match != null) {
    return {browser: "IE", version: match[2] || "0"};
  }
  var match = rFirefox.exec(ua);
  if (match != null) {
    return {browser: match[1] || "", version: match[2] || "0"};
  }
  var match = rOpera.exec(ua);
  if (match != null) {
    return {browser: match[1] || "", version: match[2] || "0"};
  }
  var match = rChrome.exec(ua);
  if (match != null) {
    return {browser: match[1] || "", version: match[2] || "0"};
  }
  var match = rSafari.exec(ua);
  if (match != null) {
    return {browser: match[2] || "", version: match[1] || "0"};
  }
  if (match != null) {
    return {browser: "", version: "0"};
  }
}

var version = "other";

//检查本地office的版本
function readOfficeVersion() {
  var word = null;
  try {
    word = new ActiveXObject("Word.application");
  } catch (e) {
    var msg = "未知";
    $("#officeVersion").append("<span>" + msg + "</span>");
    $("#isOK5").append("<span class='error'><a href='#' onclick='tip_office()'>×</a></span>");
    return null;
  }
  if (word.Version === "11.0") {
    version = "office2003";
  } else if (word.Version === "12.0") {
    version = "office2007";
  } else if (word.Version === "14.0") {
    version = "office2010";
  }    //及时关闭Word进程
  word.Application.Quit();
  return version;
}

function isBelieve() {
  if (navigator.userAgent.indexOf("MSIE") == -1) {
// alert("只支持IE浏览器！");
    $("#isBelieve").append("<span>只支持IE浏览器！</span>");
    $("#isOK6").append("<span class='error'>×</span>");
    return;
  }
  var hostname = window.location.hostname;
  var WshShell = null;
  try {
    WshShell = new ActiveXObject("WScript.Shell");
  } catch (e) {
    $("#isBelieve").append("<span>未知</span>");
    $("#isOK6").append("<span class='error'><a href='#' onclick='tip_isBelieve()'>×</a></span>");
    return;
  }
  var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
  if (hostname != "localhost" && !reg.test(hostname)) {
    var domainSFlag = false, domainEFlag = false, domainSEFlag = false, domainSSEFlag = true;
    var hostnamePrefix = "", hostnameSuffix = "";
    var indexOf = hostname.indexOf(".");
    if (indexOf != -1) {
      hostnamePrefix = hostname.substring(0, indexOf);
      hostnameSuffix = hostname.substring(indexOf + 1, hostname.length);
      try {
        WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Domains\\" + hostname + "\\http");
      } catch (e) {
        domainEFlag = true;
      }
      if (domainEFlag) {
        try {
          WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Domains\\" + hostnameSuffix + "\\" + hostnamePrefix + "\\http");
        } catch (e) {
          domainSFlag = true;
        }
      }
      if (domainEFlag && domainSFlag) {
        try {
          WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Domains\\" + hostnameSuffix + "\\" + hostnamePrefix + "\\*");
          $("#isBelieve").append("<span>您加入的可信站点不是合法的可信站点，请以\"http://\"开头！</span>");
          $("#isOK6").append("<span class='error'>×</span>");
          return;
        } catch (e) {
        }
      }
    } else {
      try {
        WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Domains\\" + hostname + "\\http");
      } catch (e) {
        domainSEFlag = true;
      }
      if (domainSEFlag) {
        try {
          WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Domains\\" + hostname + "\\*");
          $("#isBelieve").append("<span>您加入的可信站点不是合法的可信站点，请以\"http://\"开头！</span>");
          $("#isOK6").append("<span class='error'>×</span>");
          return;
        } catch (e) {
        }
      }
    }
    if ((domainSFlag && domainEFlag) || domainSEFlag) {
      $("#isBelieve").append("<span>否</span>");
      $("#isOK6").append("<span class='error'>×</span>");
      return;
    }
  } else {
    var str = [];
    for (var i = 1; i < 2000; i++) {
      try {
        str[i] = WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range" + i + "\\:Range");
      } catch (e) {
      }
    }
    var count = true;
    for (var i = 1; i < str.length; i++) {
      if (str[i] == undefined) {
        continue;
      } else {
        if (str[i] == hostname) {
          count = false;
          break;
        }
      }
    }
    if (count) {
      $("#isBelieve").append("<span>否</span>");
      $("#isOK6").append("<span class='error'>×</span>");
      return;
    }
  }
  $("#isBelieve").html("是");
  $("#isOK6").append("<span class='correct'>√</span>");
}

function detectOS() {
  var sUserAgent = navigator.userAgent;
  var isWin = (navigator.platform === "Win32") || (navigator.platform === "Windows");
  var isMac = (navigator.platform === "Mac68K") || (navigator.platform === "MacPPC") || (navigator.platform === "Macintosh") || (navigator.platform == "MacIntel");
  if (isMac) return "Mac";
  var isUnix = (navigator.platform === "X11") && !isWin && !isMac;
  if (isUnix) return "Unix";
  var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
  if (isLinux) return "Linux";
  if (isWin) {
    var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
    if (isWin2K) return "Microsoft Windows 2000";
    var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
    if (isWinXP) return "Microsoft Windows XP";
    var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
    if (isWin2003) return "Microsoft Windows Server 2003";
    var isWinVista = sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
    if (isWinVista) return "Microsoft Windows Vista";
    var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
    if (isWin7) return "Microsoft Windows 7";
    var isWin8 = sUserAgent.indexOf("Windows NT 6.2") > -1 || sUserAgent.indexOf("Windows 8") > -1;
    if (isWin8) return "Microsoft Windows 8";
    var isWin8_1 = sUserAgent.indexOf("Windows NT 6.3") > -1 || sUserAgent.indexOf("Windows 8") > -1;
    if (isWin8_1) return "Microsoft Windows 8.1";
  }
  return "other";
}

