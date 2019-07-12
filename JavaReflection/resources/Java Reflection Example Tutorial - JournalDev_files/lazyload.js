var _extends=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var s=arguments[t];for(var n in s)Object.prototype.hasOwnProperty.call(s,n)&&(e[n]=s[n])}return e},_typeof="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e};!function(e,t){"object"===("undefined"==typeof exports?"undefined":_typeof(exports))&&"undefined"!=typeof module?module.exports=t():"function"==typeof define&&define.amd?define(t):e.LazyLoad=t()}(this,function(){"use strict";var s={elements_selector:"img",container:document,threshold:300,data_src:"src",data_srcset:"srcset",data_sizes:"sizes",class_loading:"loading",class_loaded:"loaded",class_error:"error",callback_load:null,callback_error:null,callback_set:null},c="data-",l=function(e,t){return e.getAttribute(c+t)},r=function(e){return e.filter(function(e){return!l(e,"was-processed")})},o=function(e,t){var s=new e(t),n=new CustomEvent("LazyLoad::Initialized",{detail:{instance:s}});window.dispatchEvent(n)},u=function(e,t){var s=t.data_src,n=t.data_srcset,r=t.data_sizes,o=e.tagName,a=l(e,s);if("IMG"===o){!function(e,t){var s=t.data_srcset,n=t.data_sizes,r=e.parentElement;if("PICTURE"===r.tagName)for(var o,a=0;o=r.children[a];a+=1)if("SOURCE"===o.tagName){var i=l(o,s);i&&o.setAttribute("srcset",i);var c=l(o,n);c&&o.setAttribute("sizes",c)}}(e,t);var i=l(e,n);i&&e.setAttribute("srcset",i);var c=l(e,r);return c&&e.setAttribute("sizes",c),void(a&&e.setAttribute("src",a))}"IFRAME"!==o?a&&(e.style.backgroundImage='url("'+a+'")'):a&&e.setAttribute("src",a)},a="classList"in document.createElement("p"),d=function(e,t){a?e.classList.add(t):e.className+=(e.className?" ":"")+t},f=function(e,t){e&&e(t)},_="load",v="error",m=function(e,t,s){e.removeEventListener(_,t),e.removeEventListener(v,s)},b=function(e,t,s){var n,r,o=e.target;n=o,r=s.class_loading,a?n.classList.remove(r):n.className=n.className.replace(new RegExp("(^|\\s+)"+r+"(\\s+|$)")," ").replace(/^\s+/,"").replace(/\s+$/,""),d(o,t?s.class_loaded:s.class_error),f(t?s.callback_load:s.callback_error,o)},i=function(e,t){var s,n,r,o,a,i;-1<["IMG","IFRAME"].indexOf(e.tagName)&&(n=t,r=function e(t){b(t,!0,n),m(s,e,o)},o=function e(t){b(t,!1,n),m(s,r,e)},(s=e).addEventListener(_,r),s.addEventListener(v,o),d(e,t.class_loading)),u(e,t),a="was-processed",i=!0,e.setAttribute(c+a,i),f(t.callback_set,e)},e=function(e,t){this._settings=_extends({},s,e),this._setObserver(),this.update(t)};e.prototype={_setObserver:function(){var s=this;if("IntersectionObserver"in window){var n=this._settings;this._observer=new IntersectionObserver(function(e){e.forEach(function(e){if(e.isIntersecting||0<e.intersectionRatio){var t=e.target;i(t,n),s._observer.unobserve(t)}}),s._elements=r(s._elements)},{root:n.container===document?null:n.container,rootMargin:n.threshold+"px"})}},update:function(e){var t=this,s=this._settings,n=e||s.container.querySelectorAll(s.elements_selector);this._elements=r(Array.prototype.slice.call(n)),this._observer?this._elements.forEach(function(e){t._observer.observe(e)}):(this._elements.forEach(function(e){i(e,s)}),this._elements=r(this._elements))},destroy:function(){var t=this;this._observer&&(r(this._elements).forEach(function(e){t._observer.unobserve(e)}),this._observer=null),this._elements=null,this._settings=null}};var t=window.lazyLoadOptions;return t&&function(e,t){if(t.length)for(var s,n=0;s=t[n];n+=1)o(e,s);else o(e,t)}(e,t),e});!function(e,t){"use strict";var n,a,d=function(){n=new LazyLoad({elements_selector:"[data-lazyloaded]"}),a=function(){n.update()},e.MutationObserver&&new MutationObserver(a).observe(t.documentElement,{childList:!0,subtree:!0,attributes:!0})};e.addEventListener?e.addEventListener("load",d,!1):e.attachEvent("onload",d)}(window,document);