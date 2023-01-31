function readOutlookVersion() {
    let version = "other";

    let outlook = null;
    try {
        outlook = new ActiveXObject("Outlook.Application");
    } catch (e) {
        console.log(e);
        return null;
    }
    if (outlook.Version === "11.0") {
        version = "office2003";
    } else if (outlook.Version === "12.0") {
        version = "office2007";
    } else if (outlook.Version === "14.0") {
        version = "office2010";
    }
    outlook.Application.Quit();
    console.log(version);
    console.log(outlook.Version);

    return version;
}

function check(){
    let userAgent = navigator.userAgent,
        rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
        rFirefox = /(firefox)\/([\w.]+)/,
        rOpera = /(opera).+version\/([\w.]+)/,
        rChrome = /(chrome)\/([\w.]+)/,
        rSafari = /version\/([\w.]+).*(safari)/;
    let browser;
    let version;
    let ua = userAgent.toLowerCase();

    let match = rMsie.exec(ua);
    if (match != null) {
        return {browser: "IE", version: match[2] || "0"};
    }
    match = rFirefox.exec(ua);
    if (match != null) {
        return {
            browser: match[1]
                || "", version: match[2] || "0"
        };
    }
    match = rOpera.exec(ua);
    if (match != null) {
        return {browser: match[1] || "", version: match[2] || "0"};
    }
    match = rChrome.exec(ua);
    if (match != null) {
        return {browser: match[1] || "", version: match[2] || "0"};
    }
    match = rSafari.exec(ua);
    if (match != null) {
        return {browser: match[2] || "", version: match[1] || "0"};
    }
    if (match != null) {
        return {browser: "", version: "0"};
    }
    return "other ua";
}

function getOperationSystemVersion() {
    let sUserAgent = navigator.userAgent;
    let isWin = (navigator.platform === "Win32") || (navigator.platform === "Windows");
    let isMac = (navigator.platform === "Mac68K") || (navigator.platform === "MacPPC") || (navigator.platform === "Macintosh") || (navigator.platform === "MacIntel");
    if (isMac)
        return "Mac";
    let isUnix = (navigator.platform === "X11") && !isWin && !isMac;
    if (isUnix) return "Unix";
    let isLinux = (String(navigator.platform).indexOf("Linux") > -1);
    if (isLinux) return "Linux";
    if (isWin) {
        return "Windows"
    }
    return "other";
}