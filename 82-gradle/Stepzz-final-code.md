<!---
Current Directory : /Users/ranga/Ranga/git/00.courses/master-spring-and-spring-boot/22-gradle
-->

## Complete Code Example


### /build/reports/tests/test/css/base-style.css

```css

body {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
    font-size: 12pt;
}

body, a, a:visited {
    color: #303030;
}

#content {
    padding-left: 50px;
    padding-right: 50px;
    padding-top: 30px;
    padding-bottom: 30px;
}

#content h1 {
    font-size: 160%;
    margin-bottom: 10px;
}

#footer {
    margin-top: 100px;
    font-size: 80%;
    white-space: nowrap;
}

#footer, #footer a {
    color: #a0a0a0;
}

#line-wrapping-toggle {
    vertical-align: middle;
}

#label-for-line-wrapping-toggle {
    vertical-align: middle;
}

ul {
    margin-left: 0;
}

h1, h2, h3 {
    white-space: nowrap;
}

h2 {
    font-size: 120%;
}

ul.tabLinks {
    padding-left: 0;
    padding-top: 10px;
    padding-bottom: 10px;
    overflow: auto;
    min-width: 800px;
    width: auto !important;
    width: 800px;
}

ul.tabLinks li {
    float: left;
    height: 100%;
    list-style: none;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
    margin-bottom: 0;
    -moz-border-radius: 7px;
    border-radius: 7px;
    margin-right: 25px;
    border: solid 1px #d4d4d4;
    background-color: #f0f0f0;
}

ul.tabLinks li:hover {
    background-color: #fafafa;
}

ul.tabLinks li.selected {
    background-color: #c5f0f5;
    border-color: #c5f0f5;
}

ul.tabLinks a {
    font-size: 120%;
    display: block;
    outline: none;
    text-decoration: none;
    margin: 0;
    padding: 0;
}

ul.tabLinks li h2 {
    margin: 0;
    padding: 0;
}

div.tab {
}

div.selected {
    display: block;
}

div.deselected {
    display: none;
}

div.tab table {
    min-width: 350px;
    width: auto !important;
    width: 350px;
    border-collapse: collapse;
}

div.tab th, div.tab table {
    border-bottom: solid #d0d0d0 1px;
}

div.tab th {
    text-align: left;
    white-space: nowrap;
    padding-left: 6em;
}

div.tab th:first-child {
    padding-left: 0;
}

div.tab td {
    white-space: nowrap;
    padding-left: 6em;
    padding-top: 5px;
    padding-bottom: 5px;
}

div.tab td:first-child {
    padding-left: 0;
}

div.tab td.numeric, div.tab th.numeric {
    text-align: right;
}

span.code {
    display: inline-block;
    margin-top: 0em;
    margin-bottom: 1em;
}

span.code pre {
    font-size: 11pt;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    margin: 0;
    background-color: #f7f7f7;
    border: solid 1px #d0d0d0;
    min-width: 700px;
    width: auto !important;
    width: 700px;
}

span.wrapped pre {
    word-wrap: break-word;
    white-space: pre-wrap;
    word-break: break-all;
}

label.hidden {
    display: none;
}
```
---

### /build/reports/tests/test/css/style.css

```css

#summary {
    margin-top: 30px;
    margin-bottom: 40px;
}

#summary table {
    border-collapse: collapse;
}

#summary td {
    vertical-align: top;
}

.breadcrumbs, .breadcrumbs a {
    color: #606060;
}

.infoBox {
    width: 110px;
    padding-top: 15px;
    padding-bottom: 15px;
    text-align: center;
}

.infoBox p {
    margin: 0;
}

.counter, .percent {
    font-size: 120%;
    font-weight: bold;
    margin-bottom: 8px;
}

#duration {
    width: 125px;
}

#successRate, .summaryGroup {
    border: solid 2px #d0d0d0;
    -moz-border-radius: 10px;
    border-radius: 10px;
}

#successRate {
    width: 140px;
    margin-left: 35px;
}

#successRate .percent {
    font-size: 180%;
}

.success, .success a {
    color: #008000;
}

div.success, #successRate.success {
    background-color: #bbd9bb;
    border-color: #008000;
}

.failures, .failures a {
    color: #b60808;
}

.skipped, .skipped a {
    color: #c09853;
}

div.failures, #successRate.failures {
    background-color: #ecdada;
    border-color: #b60808;
}

ul.linkList {
    padding-left: 0;
}

ul.linkList li {
    list-style: none;
    margin-bottom: 5px;
}
```
---

### /build/reports/tests/test/index.html

```html
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="x-ua-compatible" content="IE=edge"/>
<title>Test results - Test Summary</title>
<link href="css/base-style.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<script src="js/report.js" type="text/javascript"></script>
</head>
<body>
<div id="content">
<h1>Test Summary</h1>
<div id="summary">
<table>
<tr>
<td>
<div class="summaryGroup">
<table>
<tr>
<td>
<div class="infoBox" id="tests">
<div class="counter">1</div>
<p>tests</p>
</div>
</td>
<td>
<div class="infoBox" id="failures">
<div class="counter">0</div>
<p>failures</p>
</div>
</td>
<td>
<div class="infoBox" id="ignored">
<div class="counter">0</div>
<p>ignored</p>
</div>
</td>
<td>
<div class="infoBox" id="duration">
<div class="counter">0.141s</div>
<p>duration</p>
</div>
</td>
</tr>
</table>
</div>
</td>
<td>
<div class="infoBox success" id="successRate">
<div class="percent">100%</div>
<p>successful</p>
</div>
</td>
</tr>
</table>
</div>
<div id="tabs">
<ul class="tabLinks">
<li>
<a href="#tab0">Packages</a>
</li>
<li>
<a href="#tab1">Classes</a>
</li>
</ul>
<div id="tab0" class="tab">
<h2>Packages</h2>
<table>
<thead>
<tr>
<th>Package</th>
<th>Tests</th>
<th>Failures</th>
<th>Ignored</th>
<th>Duration</th>
<th>Success rate</th>
</tr>
</thead>
<tbody>
<tr>
<td class="success">
<a href="packages/com.in28minutes.learngradle.html">com.in28minutes.learngradle</a>
</td>
<td>1</td>
<td>0</td>
<td>0</td>
<td>0.141s</td>
<td class="success">100%</td>
</tr>
</tbody>
</table>
</div>
<div id="tab1" class="tab">
<h2>Classes</h2>
<table>
<thead>
<tr>
<th>Class</th>
<th>Tests</th>
<th>Failures</th>
<th>Ignored</th>
<th>Duration</th>
<th>Success rate</th>
</tr>
</thead>
<tbody>
<tr>
<td class="success">
<a href="classes/com.in28minutes.learngradle.LearnGradleApplicationTests.html">com.in28minutes.learngradle.LearnGradleApplicationTests</a>
</td>
<td>1</td>
<td>0</td>
<td>0</td>
<td>0.141s</td>
<td class="success">100%</td>
</tr>
</tbody>
</table>
</div>
</div>
<div id="footer">
<p>
<div>
<label class="hidden" id="label-for-line-wrapping-toggle" for="line-wrapping-toggle">Wrap lines
<input id="line-wrapping-toggle" type="checkbox" autocomplete="off"/>
</label>
</div>Generated by 
<a href="http://www.gradle.org">Gradle 7.5.1</a> at 03-Nov-2022, 5:01:34 pm</p>
</div>
</div>
</body>
</html>
```
---

### /build/reports/tests/test/js/report.js

```js
(function (window, document) {
    "use strict";

    var tabs = {};

    function changeElementClass(element, classValue) {
        if (element.getAttribute("className")) {
            element.setAttribute("className", classValue);
        } else {
            element.setAttribute("class", classValue);
        }
    }

    function getClassAttribute(element) {
        if (element.getAttribute("className")) {
            return element.getAttribute("className");
        } else {
            return element.getAttribute("class");
        }
    }

    function addClass(element, classValue) {
        changeElementClass(element, getClassAttribute(element) + " " + classValue);
    }

    function removeClass(element, classValue) {
        changeElementClass(element, getClassAttribute(element).replace(classValue, ""));
    }

    function initTabs() {
        var container = document.getElementById("tabs");

        tabs.tabs = findTabs(container);
        tabs.titles = findTitles(tabs.tabs);
        tabs.headers = findHeaders(container);
        tabs.select = select;
        tabs.deselectAll = deselectAll;
        tabs.select(0);

        return true;
    }

    function getCheckBox() {
        return document.getElementById("line-wrapping-toggle");
    }

    function getLabelForCheckBox() {
        return document.getElementById("label-for-line-wrapping-toggle");
    }

    function findCodeBlocks() {
        var spans = document.getElementById("tabs").getElementsByTagName("span");
        var codeBlocks = [];
        for (var i = 0; i < spans.length; ++i) {
            if (spans[i].className.indexOf("code") >= 0) {
                codeBlocks.push(spans[i]);
            }
        }
        return codeBlocks;
    }

    function forAllCodeBlocks(operation) {
        var codeBlocks = findCodeBlocks();

        for (var i = 0; i < codeBlocks.length; ++i) {
            operation(codeBlocks[i], "wrapped");
        }
    }

    function toggleLineWrapping() {
        var checkBox = getCheckBox();

        if (checkBox.checked) {
            forAllCodeBlocks(addClass);
        } else {
            forAllCodeBlocks(removeClass);
        }
    }

    function initControls() {
        if (findCodeBlocks().length > 0) {
            var checkBox = getCheckBox();
            var label = getLabelForCheckBox();

            checkBox.onclick = toggleLineWrapping;
            checkBox.checked = false;

            removeClass(label, "hidden");
         }
    }

    function switchTab() {
        var id = this.id.substr(1);

        for (var i = 0; i < tabs.tabs.length; i++) {
            if (tabs.tabs[i].id === id) {
                tabs.select(i);
                break;
            }
        }

        return false;
    }

    function select(i) {
        this.deselectAll();

        changeElementClass(this.tabs[i], "tab selected");
        changeElementClass(this.headers[i], "selected");

        while (this.headers[i].firstChild) {
            this.headers[i].removeChild(this.headers[i].firstChild);
        }

        var h2 = document.createElement("H2");

        h2.appendChild(document.createTextNode(this.titles[i]));
        this.headers[i].appendChild(h2);
    }

    function deselectAll() {
        for (var i = 0; i < this.tabs.length; i++) {
            changeElementClass(this.tabs[i], "tab deselected");
            changeElementClass(this.headers[i], "deselected");

            while (this.headers[i].firstChild) {
                this.headers[i].removeChild(this.headers[i].firstChild);
            }

            var a = document.createElement("A");

            a.setAttribute("id", "ltab" + i);
            a.setAttribute("href", "#tab" + i);
            a.onclick = switchTab;
            a.appendChild(document.createTextNode(this.titles[i]));

            this.headers[i].appendChild(a);
        }
    }

    function findTabs(container) {
        return findChildElements(container, "DIV", "tab");
    }

    function findHeaders(container) {
        var owner = findChildElements(container, "UL", "tabLinks");
        return findChildElements(owner[0], "LI", null);
    }

    function findTitles(tabs) {
        var titles = [];

        for (var i = 0; i < tabs.length; i++) {
            var tab = tabs[i];
            var header = findChildElements(tab, "H2", null)[0];

            header.parentNode.removeChild(header);

            if (header.innerText) {
                titles.push(header.innerText);
            } else {
                titles.push(header.textContent);
            }
        }

        return titles;
    }

    function findChildElements(container, name, targetClass) {
        var elements = [];
        var children = container.childNodes;

        for (var i = 0; i < children.length; i++) {
            var child = children.item(i);

            if (child.nodeType === 1 && child.nodeName === name) {
                if (targetClass && child.className.indexOf(targetClass) < 0) {
                    continue;
                }

                elements.push(child);
            }
        }

        return elements;
    }

    // Entry point.

    window.onload = function() {
        initTabs();
        initControls();
    };
} (window, window.document));
```
---

### /build/resolvedMainClassName

```
com.in28minutes.learngradle.LearnGradleApplication
```
---

### /build/resources/main/application.properties

```properties

```
---

### /build/tmp/bootJar/MANIFEST.MF

```
Manifest-Version: 1.0
Main-Class: org.springframework.boot.loader.JarLauncher
Start-Class: com.in28minutes.learngradle.LearnGradleApplication
Spring-Boot-Version: 3.0.0
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
Spring-Boot-Classpath-Index: BOOT-INF/classpath.idx
Spring-Boot-Layers-Index: BOOT-INF/layers.idx

```
---

### /build/tmp/compileJava/previous-compilation-data.bin

```
 AGL... �com �in28minutes �learngradle	 �
 �earn �radle �pplication�(4a2,�cqH7�
ABS.... �org �springframework �boot �autoconfigure	 �pring �oot   AS.	    �            �        �... .! �web" �util# �pattern$ �package-info���
��t��R��>mT`%.&.' �scripting$B���7ȳ1��p�()�(.).*.+ �context, �aot$I�t�줂t4�� b�-.../. +0 �support$�!;]Ǐ�x_�i{�1.2.3.4. 5 �servlet6 �tags7 �form$e��Gr�����O�i��8.9.:. ; �jsf< �el$ܨ-�yʺO�^./�=R>M?.@.A.B.C �coreD �typeE �classreading	F �etadataG �eader�a{鞛�-lƅ]$1�WHAI$JAKML.M.BN �annotation	O �ergedP �nnotation	Q �dapt���yDKy\[b���J�kRSSIT.U.*N	V �mportW �elector�:?\o��.����g��XRY.Z.B[ �io	\ �esource���~2Ͳ% ���J`�].^._ �expression$qؒ�6�w�娈4�Sc`PaIb.c.d.e �beansf �factory	g �njectionh �oint�Z6O��OC`"�.�i.j.k.l.m �jmxn �exporto �naming$g=	�/�(i88a���wp.@$��'M�~>r���/2A�q.M$�mX�W��K;iIv;r.s.t.4u �viewv �groovy$Ι2��>�`���Rw.x.y.z.{.| �fasterxml} �jacksonC~ �json$�%�����&U쨐1G�N�B�.�.c0	� �ean� �ame� �enerator5-��ɺ90��h�NAe�.�.z� �datatype� �jdk8C��F��}�F�����.�. � �multipart$�k���ez�D�)�.�.�.� �scheduling� �config$�j{	�W�4�8�Z��.�.�.�.[� �micrometer� �common� �docs$y�ä�ss�F&U�h4S�.�.�.4� �mvc� �condition$o�	@�J%�Q��?��.�.�.z� �databind� �introspect$��Vl̐K�AB-��FϤO�.B	� �rdered��mp�b����x�lj�.�$'���C�֯��$iy�kV�.�.�.�.�.� �apache� �logging� �log4j� �spi$1a�$�-������2��A�M�B�.d	�F� �ttribute��ꘆd�1R\�ڢ�.�.*0$X]0��Q,g��b�a�ުZN�B���1���%2ոA�F�Bb�� �actory� �ware6��K/u�+�D�ң�L�D�B�� �efinition� �oader�br����bÃ
��t�C�E�.�.B� �env	� �nvironment� �apable��p��
̩�iU��.�. � �filter$���A���=��oO=%��D�B�.�.c�	��n�b�*vy��UЬ��I�.�.� �stereotype	� �ndexed9sr��5�����_652�N�.�.� �lang	� �ullable�m`��u�4�@����j�S�C�.�.B� �convert	� �onversion� �ervice��S��k�����\�.�.,$2��M)��\��E����.�.�.�.� �http� �codec~$�e���B]I�'��;��.�.t� �xslt$4���*,>�}I��m�}�F�N�N�.�.��	� �on� �ull� �ields����K�~�s�{����.�.�� �status$��34n���s�
���.�.�� �converter$A�,K@"tx�q�*.q��F�ALP� �ilterl+Dh�!�4�>����.�.�.�.� �ui+0$���i��lp�=�PH�F�Ob� �bject�[�Yn�g�#' ^W��.�.�0$�[y���K؟��v�.Ţ�.3$�g�!�40d�˃�g+���1�V­�����
�p�.�.y� �base$���YzJ^"}�|�q�F�B�C�� �onfigurable���H�>����"8��ڏ.�.�.� �format� �datetime$�4����.���<��}�.� �slf4j�O�_���u�w�}�r�F�T�.�.A�	� �ype�;<խ��ә�0&t�.�.t~$f=`���������.�.y� �async$*�݀3�c����a�M�C�.A	� �lassF#��?�Q�f��G"Q�.�.�. � �bind0$(����g��A��1��ƨF�$�S�CT� �omponent� �can	�(��yk�M��Ȇ�\+��.�.�.�.� �aop� �aspectjN$A�=#�����k���hj�c�o������z�.�.�.� �jndi0$"�m
������cq���.�.k� �notification$cȋ�'>�ө��uq�˻.�$s��]�]oO���
�bҼ.�.t� �feed$Aw��%QM���������.�$>�<��Khx<~%�.�.4� �theme$ĭ�z)�:�ls ���t�.$��O� GW�H��ܬ�.�.�.�� �number� �money$5���.���f�q�B$���.�.�.�.�.�� �tomcat"�� �fileupload$�rz#F��8���98��.�.�.�� �observation� �contextpropagation$���1q�=��F���.�. � �cors$|�#�xp/�Ρ[	#��S�P�M�� �utable� �roperty� �ources�AZ��y�l*
����'�.�.�� �cfg$3�u��C���Tw)rx�.�.�"$�a>jZ�i�T��.k$�A�Η	|��CP�9��S�I�DT� �eferredVW�c�#X��o��{R��F�B�C�A�� �utowire����c¥��D��7�Z��S��S�� �cope�Cژ�[��@�K�V��D�D�� �ependency� �escriptor����b�
Qz#�u��.�.B� �metrics$����W���l��\�rV�.�.�N$��r��M�٢���9�p�R�D�BS��� �egistrar4�N@ԃ�����	r��.�.&v$a�ܞ �5���o�G3P��.�.�N$I�B�KW�{W�"f$�.A$MF�ð:�F��L*�.�.�. � �serviceN$��8�C�zW��Z�#��.�. � �method$Q-�:����׾(����-�P�B�����	iU�x�.y$�9�T�+���l�3q!���.�.�."� �comparator$g�>@$&�㼈d]�׀čC�.�.��	� �onverter��'ro�$C�J@���F�AL� �lias� �orA�����C�BU?\9��B� �anner]s�z1 ��ۚ+2��)�.�.�.�� �server� �reactive$Ĳ�	`o�d�]�x��ҜC�B�C�� �ootstrap� �ontext���ؔÃ9�Y{�崡F��4����KD�6��'�p�̢.c$hg�`����I�����.�.�"$�D�Rj��h ҉'ʥC�E�� �xpression�Zq6n�U\&�!�[��C�RL� �epeatable� �ontainersER�31<|8��M�_��J�H�S� �hutdown� �andlersR7��7p��2@�����.�.�.�.� �instrument� �classloading� �glassfish$q�T�/��:���$��.�$�� Yf\��z��.�
��.�.�� �mime$ͪ�W���n meQR�_�S�S�$�AK� �nnotations	� �earch� �trategyi�O˰K����݄���D�N�P�� �arameter�� �iscoverer_An�k���Q#Qw���R�V�S�.�	� �tring� �alue� �esolver�A�^���U&F�}�.�.*�$��|�l7��
3����.zN�>�|�t�v�Yr��.�.�� �invoker$�D K[�C���;f�S�C�C�.�.�0	���!�X�xuȲ|��� ��.�$�2b4n�'��q��.�.��$l�, ܦ�Sxh|ya�.�.�.�� �nativex� �substitution$oIm{8�����x�ŕ�.�.��$���^a�/صE�v�0��C�T�������c�,������@�.�.�.4� �function0$�働�Z{�ʑ3��H�.�.�.� �cache� �concurrent$�<�z?ۃ3���f���M�A�PF0_���g^�cҼ�_�R�� �egistry�A��#��?�
�zR��.�.z� �module� �paramnames�Ѱ��_%-�:;o��H�� �ookC�טc�֙���9�.��H�E�B� �xception� �andler��`��.�B��+?�.�.�� �standard$x��k����h�dW�.�V�F�.�~� �ormat� �isitors$B��TI��$�BNk���.�$SBA�ߋ����q-�Э1�H�B�N�� �amed�� �older��سr�`m�D�F�B�Lb� �istable���}Ē�I��w��$�Ð.�.d� �propertyeditors$0}#!q7�����0L�P�C�A� �uto� �onfiguration� �ackage��wV�#ȳ��	j�Y	�.�.B�$���#�h��6�~��.�.�� �jdk14$s�_"�`�Z�m���F�E�T�.�.+	�� �xclude�M�� ��5�<����A�.�. � �accept$o��Z�(���#^�J��3�.�.��$��?�
�/��P�e�%�H������ҭ l4��.�.�.�� �client�$������CX�K��v��.�.t� �script$��.���뾍��/�.�.�.^� �spel� �ast${��n�H���/���E�C�����=i� ؁Z�?�X���.�.�� �deser$��\��u�
\�c`�6�.�.kN$��l���T9o�9�t�x�.�.*� �event$ޢ��Yd�<c�͔'�
�(��㋨�����y�.�. �$������Hm���L]8�.�.B� �task$B&�7� -�R�c	��.�.�� �scope$�f4�`�����ZBS�0��ܸ���F'6�(�x�.�$�H�}��f�v�֦~:���.�.�0$)�{~O��W�����L��G�.�.� �jakartaN	� �enerated�����EǑW�%1.%��C��x�L��� ��,�
5�.�.�N$����)O�(�9�F��.�.�.� �validationN$%��8>���񘌌�Kf�F�B�Hb� �ierarchical���ט� 7`٧t����.�.y� �exc$��_
�u�w�!k�>�G�M�$�	� �ode���Z�Ѣ> ����R�D���./�j�[��҉
�F�B�L����1�q��Ý����]�\�.�.t� �freemarker$��X�0���-o����ؿ�.�.��$M�!s�}���.q�P�� �rofiles�_g��(��T���u��.�.B� �log$unr�y���M� �A�.�.t� �xml$~2k�� lW��^�Q:��T�R�� �esolvable��Bw��X.�38����R�E�P��� �ditor�.��[�
1�%��u/��3�.�$L��`]� �u����z�.�.�N$��=�����ؚʊ;�.�.�� �node$S5�yȪ�����*�l��T�$�S�S�.�	� �tartup� �tep	� �ags��=�y�L1�s����.�.�� �disk$��&C��}�� �*1ދ.�.�� �protobuf$�qn�$�p�[�d��CT�ڬ�P�G�4ߩQ`��.�R�A�����3���8��p(b�0ё.�.4� �i18n$)Y��(�u����NV6��P�T�$�W�T�S�� �erializable�� �rapper	�� �rovider�.,U4�=�QZ��B��R�PY� �rotocol���\��
�vQ$�O�+��.�./� �request$�hZ�Px����A_q�.�.�~$�" �L;��^QmcѪ�A�L�C����ޤ��$B��;�Ag�.�.�� �embed� �websocket�dǚ'j%r��oB�hy�.�.�.�� �hint� �predicate$��1�BY�  5����5�.�.��$��o�H�T�����C�B������
���kp-�7�.d$Vgz�x+�N���c��.�.�� �interceptor$�h����9��N��ڹ.�.�D$1�'�~�GeEc��>*�.�$u*Q/p&�����c��.�$8�����q����D�T������'�^C�:VƤ>H�S�� �cans�o:%�u���\��4���.�.�0$vz��a{����&��:'��.�.�N$�q�Q1=�_��� z�.�.��$�|yh��? �l��>�՘�.�.*�$���Zzu��$�!�E�� �lement_��Π��ygpk��J�.t$��P� �ddT��"+"�.�.l� �access$��@�$g�t��ʉ��B�Nb���������_C ��!
�.�.x�$�oks���*��]�����.�.�0$h�R����1���nVP�.�.�� �jfr$�.�#�ǟ�6�i!�a��4!�)#��\!(ˢ%��C��=_FL��Re{#s�5�F=�%Ŝ�Zl�r�l�L�.�.�.�� �commons�	� �og"v/���'�=��;�S�I�$�R��	� �nstance� �upplier#腕�R\��ƨ��s�.�.�.�� �framework� �autoproxy$N�����i�I�.�.��$����Q�;��/�w*�.�.�0$�oF_R�=�d\��.�.�0$�b�R��W�K���y�.�.4�$�I[P�����ܿ��.�$�E:Ν
,I��T����w�P�E�A�.*	� �vent� �ublisherIn�^�Z�Ҷ��(�.�.�� �internal$��$�qm��O3_���.�.c� �parsing$NT�`M�ѻ'�)�8��.�.�.y[� �doubleparser$+4����Z:��8��N?�o����c��'��.�.�.B� �serializer0$�`ڲwHk��E��ň.�.�0$?/��!<W��´W�樊.�.&0$ؠ���z��5�_TJ�F�N�N������@��k��3>&�S�A��8�E��V��#�q�s-	�.�.�0$�#���LD�|�e^���.�.�. �� �session$���b��Ez	K��w�%�.�$w/���V��	��;��A�A��� �ccessor�[L�:o���?QL]�.�${pt��~��V��A�.�.&�$�q)0T�w=����R�.�.yD$9�f���5���ų�D�G�C�E� �xit���>�!ηZ���A1����*[w��1�,���;'�'�A�A� �rguments�~� 
	~R�^�� g�.�<�i�2_�E`�MY������R�P����@��W �&]�m�_��.�.�� �generate$&
��@�C�Υ\w�.�.�� �message$�@K�3�ɾ�PЀ`I�.�$
�Q���8"=|�3�I�� �nitializer���Q��c|X�t;=��V�A�C�� �onstructor� �rgument� �alues���;����+T�~a�9�A���U۵�LV���<��H�V�$�	�����>&�=X�!��5/�սS�M�� �essage� �ource�L�]����5��YM	�.�.�.�� �jsontype� �impl$|ܳ������R>I��C�A�E� �nable���S��nOE�4��%mh�.�$&��/��N�ֶj��g�.�.��$px��<�_��G q�V�P�M�����y[qL��Ƞ�G/���.�$>��;���ct�¹۪B�:Y�/Bq~R��ti��.�$���K{z蔣�K0P9��L�� �ifecycleVR�5�d��3��a͊�	C|�ݱݢ����'N�I�D�.�.�N	� �eterminable� �mports�R�(�+�"�_^���.�.��$Q�rx�{�*��W[`���.�$>�����?�g�`���8�SJWXϺ]�䌼�CC��v��T�A�W� �eb���x�OB9��q=ڊe��.�.�� �backoff$�>V¿�:2��x/��=�A�� �ttributes�]�krjE��S��a~ ��.�.�.���$&]�HS=�� ��t_��.�.��$:��0��>��yFۓ�xa�.�.��$�#X��u`��\�*d��.�.��$�y�B������dw�.�.�0$�Hz�Kz���>=Z�.�.�.�0N$F�.�t��t;qB�\�F�C���]5��HU�)*ܤ�� yo�H�����(���
W�.�$m1Y�΋|~�b@�:�.�.�� �ser$г}ۛ�M~��$اܸ�.�.�� �smile$�Հ�e�sy�	�KR���.�.4� �handler$�Hl���?oQeI�J	�.�$�f�ڈ��ܹ�z� �C�A�C���Α�֦|<%�t`ĕ�.�.*� �weaving$jT�1d�'�|�\=
 �P�M�� �ethod��Bk���:��?�[�.�$y�O��X�齗�����.�.�.��N$T>�M�O���ό� n��C�G�� �eneric�|�w�s�R�0�w�4�ϛ�.U$p�&��Bŉ�0�8��LX���l|��$����H<o�S�P����",5�9�u*��m�<�P��!�0SuLg1X�rH��o%�.�$ؘ�s*����?�pз��.�.^�$���J�P�SLe�,�.�.�.� �ch� �qos� �logback� �classic�<O�@�s
>��1[�&�S�� �upportvE�zZ	�+/`O-����.�.��$����`��x�Ťh��j���e�$p��%6���5�R�V�$�	� �ariable�u&�t4'�x��|���.�$a�vli�)R��/XF�+�.�$�\-&)��)?�~to�d�E�C�A�$�S�I�VW	��� �ntryDڣe��8��&1�n"�.�$T��\��T�s֎�?
L�.�.�0$��0�4M
&�0�a@��.�$@I�#0�yT�8|ԓ�:�F�E��� *�; [
����G��ĸ.�.k� �assembler$-c�y�b7n��޼���Ab��{���o@���ڢ۔�.�.Z0$U�5N���A���|�.�$Ux��������K�U��.�.y� �sym$p=��|>7D�{����Kx�.�$�mA)4�i�R����P"��.�.�� �pojo$Acʝ�~��e�l�qS �T�FT��Vr������h�}���.*$����RG�_�1b�Y,>��.�.��$~�U���OY��@V�.�.&� �bsh$; �aN{Ԙ�]'惮>�.�.��$������˓G ��w��L�R� �un� �istener	����F`�_u�	�m�.�.B� �style$�?��6Mv��p����M�P�ST� �coped� �roxy��L���]K�k��P�4��.�.c� �wiring$�<�zpZ��R� ���.�.�5$�𙡋�Xnk9a���.�.�� �cbor$�53s�ءp@Y���JgN�C>�uK�^i_��.l$�
��cbE�@z��� Z�.�$1���*i�]��+d�@��.�.��$�_��X�YH��y�/�<��A�E������\�m�%�	��
��.�.�� �jsonschema$䛛 [�9�0�)_U,�.�.c�$p����A(.���
6Q��.�.�� �jboss$ ��lqk�ab��R�P�R�.�	\� �attern���&�囼bC�|�M�T�A�� �nnotated�F/��U�����D󆉓F�.�.�"$ � ��fY��_8,�I�C����ܺ��f��轸����.�$�U��0�� �p�q�.�$C?X��̅�ş�`�y�ɁR�������{ƭ�����,�./$�㙭���?��v����R��u�մ�-��AU���)cڄ.�$YCD���m�^ !��.�.�.� �ejb�$G
�	|� j����w���.�$��x�#
O�s�� ���.�.40$�č`�m�h����Ǧ�.�.y"$Cپ���B�r�ŎR��U��A~n��ќ
������)	�㯷q��Y���.�./N$���@��k<��K�.�.*_$���;ɝ:H� Lsn�ȓ.�.d0$�;���}1C�Ro<�S�S�IY� �nput� �tream��J�X��y%�]�ц�.�.��$��B���Ƞ�f¼J�Lu�.�.4� �resource$>�ne86����I>�_�V��>��Q:8C����?�2��.�.*� �index$(�-M��	w�z�jɧ�S���R�"�
j�(��zL���.�.y�$���H�Q
J��z�wL��R�T�P�� �arameterized�� �eference'7�=:9x�	x�d���.�$槿&��s���Jbv�2�.�$��f������k�/t��S�T�.�	� �hrowing�T�gDԟ�ME���[h�.�.�N$=�������/����.�.Z� �buffer$�9�T�O������C3�.�.k� �metadata$�P��?Z�h[h�TL޹.Z$O��5��r��9	Bn0��R�B�S�� �ingleton��(Mܱ=��>L@P�H�U�.�.�� �adapter$хa����*:3�
TA�.�.�N$��Q���xj"��,���P�P�F��� �ost� �rocessorO'nG�J��76ky�,��A�L�R�\��Q���єc���'m� �M�V�M�� �ulti�� �ap����Իd��I�ßq<�.�.�N$�u��Ww���@wɅ
p��.�.��$s���!��
C�97��d-�.�.�� �beanvalidation$֬-9#��x����΂�.�.l0$'�l�$/N�<����x�o�.�.�� �ext$�,�IH
gق�z9d�.B$0k?g�=Gȁs*��;�.�.��$f�U��;�u �]\�m�.�.�0$�H��R Η�~�.�.�� �unit$�����lK#�<S ��.�.��$�8n�8��7CL��.�.��$#GP��<�.u�0_7H�����:�D��
DoU��.�.�N$@dg�q�Q9)�
o�����m�鹸،0#��O��.�.��$��^�I7t���+<F"�R�$�P�� �ackages	�^��u4Bb�ٮ�1�.�.�� �simple$��x�V�\'��MzU��.�$�ڨ�1P�� W��pW���.�$o
)p %K�.S�j9�.�.�0$���YZ���E�L������&��`���RK��=�.�.��$ڤ�p��i�7�I�'T��P�P�����󚺢ݘ{e<O�.�.�� �std$f����b�9����٥y�.�	.�0$�_ ����>iȸn���	.�	.��	 �transport$#큷�,l
'=�\���	.�	.c�	 �serviceloader$�D�|2�`Ub��χ	.�	.��$�uA��,� m�7��(�	S��a�K��/e�4o:���Ɗ	A��	 �pi7�f�a�/j��@�a�>�	.�C]N�9XY���*�nП�	.�$��2��@z���&kJ���	.�	.�N$�J	�|���_�oiq�̐	.�$kG>Ϋ����䨍D>Α	.�$�qB�,��/��Ɠ�^Ie�	.�	.��$����a.�+0.�5�	.�$�sC�2 �x���m�Z�	. $��*��l�u��D����	.:$@�H�e��R�-�$c�	.��	 �jsr310%�F�O٫��=�J�	P��t�{)�l�Ɣ� �l��	.�	.�0$ʐ�ջՎ �4�ۜ	.�	.��$�7��/�.�3e�ΰ<���	.�$��)�+�j��T�7���	.�	.��$G[E�Fz*Z:��5K�	.�	.t�	 �document$�YkR��k���7�	.�C�ZН'�m�_ [h��ե	.�	.��$��
^�5�˚ւ��z1�	V����*��~ݡ��Ǻ��	.�	.�"$�n��yX�^�����	T�	.�	.��$��X��u4{jf
�+������G �
��"1�	.�	.��$]/^���#2>��:�	.�	.cN$c����<&.�� ��İ	A��	7����
1R:i�~��
��	.4$r�O���צϋ߂�	.�	.c,$��k���eh��2��H�	.�	.��$�Z&��7ĕݝ�ﻶ	.�	.��	 �feature$t�{��=-��n���	.�$���f�N[p�.��Ⱥ	R��Q삉��	�=e{���� � = ���� � � H J � � R � X =���� ` � � �%�(-���18����]�ip��q�r��������������������������������������������������������������������������������������������������������������������������������������������������	�	����	�	�	�	�	�	�	��	��	���	�	�����	�	��	����	�	�	�	�	�	�	 � �� � ��  �� � �module-info of 'com.fasterxml.jackson.datatype.jdk8' has changed�  � � � �� �  � �module-info of 'org.apache.tomcat.embed.el' has changed� � � � �  � �module-info of 'org.apache.logging.log4j' has changed� � � �	 � �� �  � � � � � ��� � �� � =R`���������������������X�����������������������������J�����	�	�	 � �� � � � 
 � 
����	������ � � � �� � 
� � � � �� � � � ��	 � �������� � � � � � �J � �� � �module-info of 'org.slf4j' has changed� �� �  y �module-info of 'com.fasterxml.jackson.core' has changed� =� � 
�  � � ���� � �`���� � � � ����� � � � � � �module-info of 'ch.qos.logback.classic' has changed� � � ������ � � � � � � � � � 
 � �� � ���� � �� � �� � � � �module-info of 'com.fasterxml.jackson.databind' has changed� � � �� � ��
�� �  � � � � � � � � � �	 J �� � �� � � � � � � � � � � �  � �� � � � �module-info of 'com.fasterxml.jackson.annotation' has changed� � � �� � �� � �� � � � � � =R���� � � � �module-info of 'com.fasterxml.jackson.module.paramnames' has changed�  �  � X � � � � � �� � � � �� � � � 
 �  �  � ���� � � � � � � � � S �� � �source retention annotation 'value' has changed� 
 � �� �  � ��� � ��� � �module-info of 'jakarta.annotation' has changed� �� � � � ������ � � � � � � � �� � 
� � � � � � � � � � �� �	 � �	 
����	������ � �module-info of 'org.apache.tomcat.embed.websocket' has changed�	 �module-info of 'ch.qos.logback.core' has changed� �� �	 �module-info of 'com.fasterxml.jackson.datatype.jsr310' has changed�	 �� � ���� � � � ���� �	 �module-info of 'org.apache.tomcat.embed.core' has changed�	 �� � � � � � � �	 �%�(-���18����]�ip��q�r��������������������������������������������������������������������������������������������������������������������������������������������������	�	����	�	�	�	�	�	�	��	��	���	�	�����	�	��	����	�	�	�	�	�	�	 � � � ��� � � �	 �  �   �	.�	A�	G�	L�	/�	/�	/	
�	 �java        
```
---

### /build/tmp/compileTestJava/previous-compilation-data.bin

```
 TAGL... �com �in28minutes	 �learngradle
 � �earn �radle
 �pplication �ests��𚆪ϛ^>�W�5T.... �org �junit �jupiter �api
 �est   TBS.... .! �springframework" �boot# �test$ �context
% �pring& �oot    �            �        �'.(.).*. + �web, �util- �pattern. �package-info���
��t��R��>mT`/.0 �jakarta1 �activation�C9a��� $]�o�"2.3. 4 �scripting.B���7ȳ1��p�()�5.6.7. $8 �aot.I�t�줂t4�� b�9.:.;.<.= �net> �bytebuddy? �utility@ �visitor.���y,@�z�n5HA.B.C.*$D �support.�!;]Ǐ�x_�i{�EIFTG.H.I �extension
J �nstances���F�̽'B�p����K.L.M.N. #$O �jdbc.�L>�]؇O�\���aP.Q.R.S.*T �servletU �tagsV �form.e��Gr�����O�i��W.X.Y.*Z �jsf[ �el.ܨ-�yʺO�^./�\R].^._. ` �corea �io
b �esource���~2Ͳ% ���J`�c.;.SZźD>M}7v�W�xd.e. f �expression.qؒ�6�w�娈4�ScgPhIi.j.k. l �beansm �factory
n �njectiono �oint�Z6O��OC`"�.�p.q.Mr �testng.\{Q����O��5�e�s.t.u.v. w �jmxx �exporty �naming.g=	�/�(i88a���wz.{.|._} �type~ �classreading.��'M�~>r���/2A�.�._� �annotation.�mX�W��K;iIv;�.�.�.S� �view� �groovy.Ι2��>�`���R�.�.�.�.�.� �fasterxml� �jackson`� �json.�%�����&U쨐1�.�.�� �datatype� �jdk8C��F��}�F�����.�.*� �multipart.�k���ez�D�)�.�.�. � �scheduling� �config.�j{	�W�4�8�Z��.�.�.�.a� �micrometer� �common� �docs.y�ä�ss�F&U�h4S�.�.�.S� �mvc� �condition.o�	@�J%�Q��?��.�.M,.���L��T�EI3/�.�.;� �privilege.������u���a`;���.�.�.�� �databind� �introspect.��Vl̐K�AB-��Fϱ.�.�.<� �agent� �builder.�E�e�ʜ���R���K�.�.'���C�֯��$iy�kV�.�.�.�.�.� �apache� �logging� �log4j� �spi.1a�$�-������2��.�.�.�.�. � �mock� �http� �server� �reactive._��;�j��ڄmS�3m�.�.7D.X]0��Q,g��b�a�޹ZN�B���1���%2��I�EG� �xecutable� �nvokere�!țt֔"�8;\?��.�.�.�.N+T� �client.����9���q�wF��C�E�.�._� �env
� �nvironment� �apable��p��
̩�iU��.�.*� �filter.���A���=��oO=%��N�.�. � �lang
� �ullable�m`��u�4�@����j�.�.��.-]v�8��o���@���.�. 8.2��M)��\��E����.�.�.M� �junit4� �statements.�����qI����
\�.�.�.�. �� �codec�.�e���B]I�'��;��E�S�.�.�.M
%� �xtension����D��$�v˱�]q�.�.�� �xslt.4���*,>�}I��m�}�F�N�N�.�.��
� �on� �ull� �ields����K�~�s�{����D�B�$�N�N�.�.;� �nullability
� �ever�
� �y� �efaultс��F��h�U�]x��.�.�� �converter.A�,K@"tx�q�*.q��.�.�� �status.��34n���s�
���.�.�.�. � �ui$D.���i��lp�=�PH�.�.�.<� �dynamic� �scaffold.]��H�ZT0�>��p�F�Oi� �bject� �actory[�Yn�g�#' ^W��C�PG� �arameter� �ontextnf��	��n��s�dF��.�.�D.�[y���K؟��v�.Ţ�.R.�g�!�40d�˃�g+�.�.�.�.�.<� �implementation� �bytecode� �assign� �primitive.����N��w��m�@r��F�Bi� �ean���1�V­�����
�p�.�.�� �base.���YzJ^"}�|�q�.�.�� �inline.c�+Mw�$܇"4��.�.�.�� �htmlunit� �webdriver.
&�Ԡ`T�Ͽ.�.�. � �format� �datetime.�4����.���<��}�.� �slf4j�O�_���u�w�}�r�.�.��.f=`����������.�.�� �async.*�݀3�c����a�.�.�� �reference.��7�'�����nU��.�.�.*� �bindD.(����g��A��1����.�.�.�. � �aop� �aspectj�.A�=#�����k����.�.�. � �jndiD."�m
������cq���.�.u� �notification.cȋ�'>�ө��uq���.�.s��]�]oO���
�b��.�.MD.������9P �� ���.�.�� �feed.Aw��%QM���������.�.NO.�� ��2���`���:�.�.�� �attribute.ܶ�<�l��셒�ڲ�D�B�$�N�N�.�.�.�?�
��
�����.�#�8�h&���.�.j�.>�<��Khx<~%�.).��O� GW�H��ܬ�.�.S� �theme.ĭ�z)�:�ls ���t�.�.�.�� �number� �money.5���.���f�q�B$���.�.�.�.�.�� �tomcat,�� �fileupload.�rz#F��8���98��.�.�!��9�G=��צy��.�.�.�� �observation� �contextpropagation.���1q�=��F�䁊.�.M� �hint.� j��ד��������؍.�.� �objectweb� �asm�ڎN��Ȧ����3���.�.*� �cors.|�#�xp/�Ρ[	#��.�.�� �cfg.3�u��C���Tw)rx�.�.�,.�a>jZ�i�T��.�.����@����}��UЀ�.u.�A�Η	|��CP�9��.�.��E�hF�-\M���H�M�M�U�$
� �se� �ain� �ethodԤJ������4F��.�.� �assertj`{~�i^� 8��}��F�B�C�A�.�
� �utowire����c¥��D��7�Z��S��.�.�.<� �description�.QbhټM�pIe�`�[߰E�W�� �eb��|	������`�L��D�D�� �ependency� �escriptor����b�
Qz#�u�.�._� �metrics.����W���l��\�rV�.�.��.��r��M�٢���9�p�.�.�� �request.2�'\�Za�G��}��.�.3�.a�ܞ �5���o�G3P��C�B�.M
� �ootstrap�_*h�#`k���������.�.��.I�B�KW�{W�"f$�.|.MF�ð:�F��L*�.�.�.*� �service�.��8�C�zW��Z�#���.�.*� �method.Q-�:����׾(����.�.�9�T�+���l�3q!����-�P�B�����	iU�x�.�.�. ,� �comparator.g�>@$&�㼈d]�׀��W�� �ithO�jR0�by~p��3���F�A�.�
� �lias� �orA�����C�BU?\9��M�C�T��� �anager��(��uE�Dr�,��.� �params�4:�{�d�����X/r�.�.� �apiguardian ����0|�פ�v��&�.�.�.���.Ĳ�	`o�d�]�x����.�.�� �collection.�Sd�>���,�]��.j.hg�`����I�����.�.�,.�D�Rj��h ҉'��I�P�A�.�



�XE�v����18��v�.�.�.�. � �instrument� �classloading� �glassfish.q�T�/��:���$��.�.�� �mime.ͪ�W���n meQR�_�.�.�._� �convert�.�� Yf\��z��.�
��R�� �esolver	�[��v�u��[����D�N�P�._
�� �ame� �iscoverer_An�k���Q#Qw�͇.�.7�.��|�l7��
3��ډN�$�C�EG��
� �amespace��a��X�����=P��.���>�|�t�v�Yr��.�.�� �invoker.�D K[�C���;f�.�.�h4��@��F��	OΓ.�.jD.�2b4n�'��q��.�.�+.�U�6E-�h�;H����ܗ.�.��.l�, ܦ�Sxh|ya�.�.��.�;��G�N9�|�5��.�.�.�� �nativex� �substitution.oIm{8�����x�ŕ�C�C�M�� �erged�� �onfigurationnM��y�
��
�K�.�.��.���^a�/صE�v�0��S�� �toreK��[�?(*p@^��w�C�T�.k
� �ype� �onverter����c�,������@�.�.�.S� �functionD.�働�Z{�ʑ3��H�.�.�. � �cache� �concurrent.�<�z?ۃ3���f���.�.�� �module� �paramnames�Ѱ��_%-�:;o��.�.�� �standard.x��k����h�dW�.�.�� �field.F]A���d���V�4!�.�V�F�.��� �ormat� �isitors.B��TI��$�BNk���B�C�T�A�.�
� �bstract�� �ootstrapper�Fk�˪mrK_8J��.�.SBA�ߋ����q-�Э1�H�B�N�� �amed�� �older��سr�`m�D��.�.�+.����ȣj�[r�i+���F�B�Li� �istable���}Ē�I��w��$���.�.k� �propertyeditors.0}#!q7�����0L��.�.<� �pool._^sΰ)��?آ��t1C�.�._�.���#�h��6�~��.�. g�z����Y �kM�.�.�.��� �match.�*�(�
u)$���W�.�.�� �jdk14.s�_"�`�Z�m����.�.*� �accept.o��Z�(���#^�J��3�.�.��.��?�
�/��P�e�%��B�C�T�D�����bn֭�[�|5p �c}�.�.�� �subclass.���/2sE�O�çp�.�.�.���.������CX�K��v��.�.�� �script.��.���뾍��/�.�.�.e� �spel� �ast.{��n�H���/���.�.�� �deser.��\��u�
\�c`�6�.�.u�.��l���T9o�9�t�x�.�.7� �event.ޢ��Yd�<c�͔'�.�.߱�76$���Fmɝ�!�.�.*�.������Hm���L]8�.�._� �task.B&�7� -�R�c	Տ.�.�� �scope.�f4�`�����ZB�.�.�H�}��f�v�֦~:�Փ.�.����o%>�[�42�Y�S�$�
� �tatus4a�Q�������kחG�.�.0�
� �enerated�����EǑW�%1.%��.�.�D.)�{~O��W�����L��C�A�BG� �efore� �ll� �allback	W\I�Y(��X�ϖ�M�.�.��.����)O�(�9�F��.�.�. � �validation�.%��8>���񘌌�Kf�L�C��� �oaderiC��,|�65�f�c�X�F�B�Hi� �ierarchical���ט� 7`٧t���.� �opentest4j��)���!�|!���л�B�C����?;��c����/X�.<.ﭫ���_0��l�����.�.�� �exc.��_
�u�w�!k�>�G�.�.��.6�)�]>1'�tDV�����Ggs迤nҋp�D�->��.�.M8.Ț4�����Lۮ��(�.�.�� �freemarker.��X�0���-o����ؿ�P�� �rofiles�_g��(��T���u��.�.��.M�!s�}���.q�.�._� �log.unr�y���M� �A�.�.�� �xml.~2k�� lW��^�Q:��T�R�� �esolvable��Bw��X.�38����.�.L��`]� �u����z�.�.��.��=�����ؚʊ;�.�.�� �node.S5�yȪ�����*�l��.�.���D7e;�DS_U���.�.�� �disk.��&C��}�� �*1��.�.����V��Z"���0m��.�.���v'8 }\]����.�.�� �protobuf.�qn�$�p�[�d���A�T�A�.�
� �ot� �ttributes&�� َ]k�� + 6���.�.S� �i18n.)Y��(�u����NV6��P�T�$�W�T�S�� �erializable�� �rapper
�� �rovider�.,U4�=�QZ��B��.�.C�.�hZ�Px����A_q��.�.��.�" �L;��^QmcѪ�.�.�� �embed� �websocket�dǚ'j%r��oB�hy�.�.�.��� �predicate.��1�BY�  5����5�.�.��.��o�H�T������B��>�b�W���g�2�*�.k.Vgz�x+�N���c��.�.�� �interceptor.�h����9��N��ڀ.�.�}.1�'�~�GeEc��>*�.�.u*Q/p&�����c��.�.8�����q����.�.�.�.����.�O�u�a2�H$-�0ʈD�T�.�
�����'�^C�:VƤ>H�.�.�D.vz��a{����&��:'�.�.��.�|yh��? �l��>�՘�.�.��.�q�Q1=�_��� z�.�.7�.���Zzu��$�!�.�.M+.��C�t����^�.�.��P� �ddT��"+"�.�.v� �access.��@�$g�t��ʉ��.�.�� �modifier.vTz�6М���IW)��B�Ni���������_C ��!
�.�.��.`g��.\KC$hI���yP�.�.��.�oks���*��]�����.�.�D.h�R����1���nVP�C�E�T�AG� �fter� �xecution��k�iy'E���2�pA�.�.�� �jfr.�.�#�ǟ�6�i!�a�'"����LD��/Ʒ
�.�.M� �transaction.��vU￱�i��_u�C�A������õF���?<�v�R�.�.n�^�,�Q)_����.�.�.�� �framework� �autoproxy.N�����i�I�.�.��.����Q�;��/�w*�.�.�D.�oF_R�=�d\˼.�.�D.�b�R��W�K���y�.�.�.���.	|+xK�D{!��_�I�.�.S�.�I[P�����ܿ��.�.�E:Ν
,I��T����w�W�EG� �xtend�2�7<����/��
���$�P�E�A�.7

� �vent� �ublisherIn�^�Z�Ҷ��(�.�.�� �internal.��$�qm��O3_���.�.j� �parsing.NT�`M�ѻ'�)�8��.�.�.�a� �doubleparser.+4����Z:��8��.�.�._� �serializerD.�`ڲwHk��E����.�.�D.?/��!<W��´W���M�E�.�.� �parallel
�� �ode������>N^��<�.�.�� �setup.��i�}C}�/�Z���_�.�.3D.ؠ���z��5�_TJ�F�N�N������@��k��3>&�.�.�D.�#���LD�|�e^���.�.�.*�� �session.���b��Ez	K��w�%�.�.w/���V��	��;��A�A�� �ttribute� �ccessor�[L�:o���?QL]�.�.{pt��~��V��A�.�.3�.�q)0T�w=����R�T�.�.�.�.� �platform� �commons�
� �estableŧw܅Z�^P�R��ǁ.�.�}.9�f���5���ų�D�.�.<�.T@��z�Ғ������S�.�[�i�2_�E`�MY������.�.�� �generate.&
��@�C�Υ\w�.�.�� �message.�@K�3�ɾ�PЀ`I�.�.
�Q���8"=|�3�L�E��� �istener�s�,ᅟ)�ь�9�:�.�.M�.BwZg�:,٤A׬�3��S�M�� �essage� �ource�L�]����5��YM	�.�.�.�� �jsontype� �impl.|ܳ������R>I��.�.&��/��N�ֶj��g�.�.��.px��<�_��G q�P�F�C�A�
�� �ailure� �rocessor�ӑ<�[�t�󜕤.�.>��;���ct�¹ۥ.�.���K{z蔣�K0P9��	C|�ݱݢ����'N�.�.��.Q�rx�{�*��W[`���p�$8��,Y��8�@Dߨ.�.>�����?�g�`���8�C�E�� �ach��#
=�z�7�d��.�.�� �backoff.�>V¿�:2��x/��=�.�.;� �dispatcher.�Ν�׹
p����Ȕ�Ͳ.�.�.���.&]�HS=�� ��t_��.�.��.:��0��>��yFۓ�xa�.�.��.�#X��u`��\�*dع.�.�D.�Hz�Kz���>=Z�.�.��.�y�B������dw�.�.�.�D�.F�.�t��t;qB�\�o�H�����(���
W�.�.m1Y�΋|~�b@�:�.�.�� �ser.г}ۛ�M~��$اܸ�.�.�� �smile.�Հ�e�sy�	�KR���.�.S� �handler.�Hl���?oQeI�J	�C�E����ҋ^%��)��T9��
��.�.�f�ڈ��ܹ�z� �.�.7� �weaving.jT�1d�'�|�\=
 ��P�M����Bk���:��?�[�.�.y�O��X�齗�����.�.�.���.T>�M�O���ό� n��.�.7�.p�&��Bŉ�0�8��L\���l|��$����H<o�P�� �roperty!�0SuLg1X�rH��o%�.�.ؘ�s*����?�pз��.�.e�.���J�P�SLe�,G�Mt�w�����Pِ��.�.�.� �ch� �qos� �logback� �classic�<O�@�s
>��1[�&��Z�<v��5q���.�.��.����`��x�Ťh��j���e�$p��%6���5�R�V�$�
� �ariable�u&�t4'�x��|���.�.a�vli�)R��/XF�+�.�.�\-&)��)?�~to�d�.�.T��\��T�s֎�?
L�.�.�D.��0�4M
&�0�a@��.�.@I�#0�yT�8|ԓ�:�.�.u� �assembler.-c�y�b7n��޼���.�.^D.U�5N���A���|�.�.Ux��������K�U��.�.�� �sym.p=��|>7D�{����Kx�.�.�mA)4�i�R����P"��EG� �xtensions�q����w���ޠ��.�.�� �pojo.Acʝ�~��e�l�qS �.7.����RG�_�1b�Y,>ځ.�.��.~�U���OY��@V�.�.0��q:*�L"t��=k#6�.�.��.������˓G ��w��.�.3� �bsh.; �aN{Ԙ�]'惮>�.�.�e	���%�0���5�.�._� �style.�?��6Mv��p����.�.j� �wiring.�<�zpZ��R� ���.�.�T.�𙡋�Xnk9a���.�.�� �cbor.�53s�ءp@Y����.v.�
��cbE�@z��� Z�.�.1���*i�]��+d�@��.�.��.�_��X�YH��y�/�<�.�.�� �jsonschema.䛛 [�9�0�)_U,�.�.N�.b贋��7�U'j$�ڟ.�.�� �jboss. ��lqk�abâ.�.j�.p����A(.���
6Q��.�.<� �build.@b�IJs��g���	4p�R�P�R�.�
b� �attern���&�囼bC�|���g�9��X��fy�.�.�,. � ��fY��_8,�.�.�U��0�� �p�q�.�.�� �rules.��0��޺d��[����.�.<� �matcher.�TD��a�Vg�0��.�.C?X��̅�ş�`�y�ɶ.�.�� �member..=gI� |ĺ�ӹ.C.�㙭���?��v����R�P���u�մ�-��AU���)cڼ.�.YCD���m�^ !��.�.�. � �ejb�.G
�	|� j����w���.�. � �stereotype.��x�#
O�s�� ���.�.SD.�č`�m�h����Ǧ�.�.�,.Cپ���B�r���R��U��A~n��ќ
�����.�.���^cX�Ǉ���M���.�.C�.���@��k<��K�P�.<��5ʟo}��Ay��.�.7f.���;ɝ:H� Lsn���C�E�T����>��x�P���D�[��.�.kD.�;���}1C�Ro<�S�S�I]� �nput� �tream��J�X��y%�]�ц��.�.��.��B���Ƞ�f¼J�Lu�.�.S� �resource.>�ne86����I>�_�.�.7� �index.(�-M��	w�z�jɧ��.�.
�f���Jc�DW~�R�T�P�� �arameterized�� �eference'7�=:9x�	x�d���.�.��.���H�Q
J��z�wL��Z�G?9$�L3�2
���.�.槿&��s���Jbv�2�.�.��f������k�/t��.M.��ERtM�m.�����.�.N,.�jT��2"�����$��.�.�T�Bc�*]���C�:�.�.��.=�������/����.�.�� �auxiliary.F͡gy�Q
�{���:��.�.^� �buffer.�9�T�O������C3�.�.�}.��"m�!�A��
(�"�8�.�.u� �metadata.�P��?Z�h[h�TL��D�L�C�A�C��	 �ache�	 �ware���	 �elegateK��������܃	.^.O��5��r��9	Bn0��ωD���Aq>	��.�	.�	.��.��Q���xj"��,���	.�	.��	 �adapter.хa����*:3�
TA�	.�	.��.�u��Ww���@wɅ
p�	.�	.��	 �beanvalidation.֬-9#��x����΂�	.�	.��	 �constant.=���=B�@B��;�*�	.�	.��.s���!��
C�97��d-�	.�	.vD.'�l�$/N�<����x�o�	.�	.��	 �ext.�,�IH
gق�z9d�	._.0k?g�=Gȁs*��;�	.�	.��.f�U��;�u �]\�m�	P�	P�	IF�	 �nstance�	 �ost���ƕ7 WN>�F8!e��	.�	.�D.�H��R Η�~�	.�	.��	 �unit.�����lK#�<S ��	.�	.��.�8n�8��7CL��	.�	.��.#GP��<�.u�0_7H�����:�D��
DoUȩ	.�	.��.@dg�q�Q9)�
o���	.�	.��.��^�I7t���+<F"�	.�	.��.<��'d�%p5���	.�	.��	 �simple.��x�V�\'��MzU��	.�.�ڨ�1P�� W��pW��	.�.o
)p %K�.S�j9�	.�	.��	 �socket.2�5��0�S~ �W��	.�	.�D.���YZ���E�	.�	.��.ڤ�p��i�7�I�'Tǻ	.�	.�	.���.�ZiN���x����	.�	.��	 �result.ڱ��d�
�Y�k���	.�	.��	 �std.f����b�9����٥y<�4љ��>�'ӺP5�9�	.�	.�D.�_ ����>iȸn���	.�	.��	 �transport.#큷�,l
'=�\���	.�	.j�	 �serviceloader.�D�|2�`Ub����	.�	.��.�uA��,� m�7��(�	.�	.��	 �response.�����dŧ)��	A��	 �pi7�f�a�/j��@�a�>�	.�`]N�9XY���*�nП�	.�	.��.�J	�|���_�oiq���	.�	.|�.��2��@z���&kJ���	.�	.�D.kG>Ϋ����䨍D>��	.�.�qB�,��/��Ɠ�^Ie�	.�	.��.����a.�+0.�5�	.*.��*��l�u��D�����	.�.�sC�2 �x���m�Z��	.Y.@�H�e��R�-�$c�	.��	 �jsr310%�F�O٫��=�J�	P��t�{)�l�Ɣ� �l��	.�	.�D.ʐ�ջՎ �4���	.�	.��	 �enumeration.[����%g-��j�`ŵ��	.�	.��.�7��/�.�3e�ΰ<���	.�.��)�+�j��T�7���	.�	.��	 �loading.)gi��>��ؼ)g�j��	.�	.��.G[E�Fz*Z:��5K�	.�	.��	 �document.�YkR��k���7��	M�	H�	$�	C�	D�	.�
�	 �irties�
�	 �ierarchy����zӒ0����fX���	.�`�ZН'�m�_ [h����	.�	.��.��
^�5�˚ւ��z1�	.�	.�,.�n��yX�^�����	T�
.�
.��.��X��u4{jf
�+�
C�������G �
��"1�
.�
.��.]/^���#2>��:�
.�
.j�.c����<&.�� ��ć
A��	7����
1R:i�~��
��
.S.r�O���צϋ߂�
.�
.j8.��k���eh��2��H�
.�
.��	.�Z&��7ĕݝ�ﻍ
.�
.��
 �feature.t�{��=-��n���
.�.���f�N[p�.���l/ �module-info of 'jakarta.activation' has changedE � � � � �
 \ �� � � g � � ��'2�5AK����PW����d��psz�����������������������������������������������������������������������������������������������������������������������������������������������	���	�	���	��	���	�	�	�	�	������	�	���	�	��	�	��	��	��	�	��	���	�	�	�	�	�	�	��	��	����	�	�����	��	��	����
�
��
�
�
�
�
 � � � �module-info of 'com.fasterxml.jackson.datatype.jdk8' has changed�  � �module-info of 'org.apache.tomcat.embed.el' has changed� � � �module-info of 'org.apache.logging.log4j' has changed� � � �
 � g�����������
\�����������������	 � �
 �  � 
�	���	����	�� � �� � "������9�������	����	c�������	�������� � � � �	 � ��  �module-info of 'org.junit.jupiter.api' has changed� ���� � � � �module-info of 'org.slf4j' has changed� �module-info of 'com.fasterxml.jackson.core' has changed� � � �g���� � � � � � �module-info of 'org.objectweb.asm' has changed� � � �module-info of 'ch.qos.logback.classic' has changed � � � � � �  � �module-info of 'org.assertj.core' has changed� �
 � � � � � � � �� � �module-info of 'com.fasterxml.jackson.databind' has changed�  �  � � � �module-info of 'org.junit.jupiter.params' has changed� �module-info of 'jakarta.xml.bind' has changed� �module-info of 'org.apiguardian.api' has changed� �E�����������	������ � �
 � � � �module-info of 'org.junit.platform.commons' has changed� �� � � � �module-info of 'com.fasterxml.jackson.annotation' has changed� ���� � � � � � � � � � ����� � �module-info of 'com.fasterxml.jackson.module.paramnames' has changed� � � \ � � � � � �
 � � � 	���������	 � � � ��� � �module-info of 'net.bytebuddy.agent' has changed� � � �source retention annotation 'value' has changed� � � ��� �	 � � �
 � �module-info of 'org.opentest4j' has changed�  � ��������	 � �module-info of 'jakarta.annotation' has changed� � � ����� < �module-info of 'net.bytebuddy' has changed� � � � �	 
�	���	����	�� � �module-info of 'org.apache.tomcat.embed.websocket' has changed�	 �module-info of 'ch.qos.logback.core' has changed� �� �	 �module-info of 'com.fasterxml.jackson.datatype.jsr310' has changed�	 �� � �� �	 �� �	 �module-info of 'org.apache.tomcat.embed.core' has changed� � � � �
 ������  �module-info of 'org.junit.jupiter' has changed�
 ��'2�5AK����PW����d��psz�����������������������������������������������������������������������������������������������������������������������������������������������	���	�	���	��	���	�	�	�	�	������	�	���	�	��	�	��	��	��	�	��	���	�	�	�	�	�	�	��	��	����	�	�����	��	��	����
�
��
�
�
�
�
 � �  �   �
.�
T�
A�
G�
L�
/�
/�
/	

�
 �java        
```
---

### /src/main/resources/application.properties

```properties

```
---
