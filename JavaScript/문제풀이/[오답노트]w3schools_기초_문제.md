# w3schools.com 기초 문제 test

출처: https://www.w3schools.com/js/exercise_js.asp?filename=exercise_js_variables1
태그: 오답노트

[문제] Use the correct Array method to remove the last item of the `fruits`
 array.

```jsx
const fruits = ["Banana", "Orange", "Apple"];
/* 빈칸 */ fruits.pop();
```

[문제] Use the correct Array method to add "Kiwi" to the `fruits`array.

```jsx
const fruits = ["Banana", "Orange", "Apple"];
/* 빈칸 */ fruits.push("Kiwi");
```

[문제] Use the correct Date method to extract the year (four digits) out of a date object.

```jsx
const d = new Date();
year = /* 빈칸 */ d.getFullYear();
```

[문제] Use the correct Date method to get the month (0-11) out of a date object. *(오답은 아님)*

```jsx
const d = new Date();
month = d.getMonth();
```

[문제] Use the correct Date method to set the year of a date object to 2020.

```jsx
const d = new Date();
d.setFullYear(2020);
```

[문제] Use the correct Math method to round a number to the nearest integer. *(오답 아님)*

```jsx
let x = Math.round(5.3);
```

[문제] Use the correct Math method to get the square root of 9.

```jsx
let x = Math.sqrt(9);
```

[문제] Use the `getElementsByTagName` method to find the *first* `<p>` element, and change its text to "Hello". *(오답아님)*

```jsx
<p id="demo"></p>

<script>
document.getElementsByTagName("p")[0].innerText = "Hello";
</script>
```

[문제] Change the text of the first element that has the class name "test".

```jsx
<p class="test"></p>
<p class="test"></p>

<script>
 document.getElementsByClassName("test")[0].innerHTML = "Hello";
</script>
```

스타일 바꾸기(오답아님)

[문제] Change the text color of the `<p>`element to "red".

```jsx
<p id="demo"></p>

<script>
document.getElementById("demo").style.color = "red";
</script>
```

[문제] Change the font size of the `p`element to 40 pixels.

```jsx
<p id="demo"></p>

<script>
document.getElementById("demo").style.fontSize = "";
</script>
```

[문제] Use the CSS `display` property to hide the `p`
 element.

```jsx
<p id="demo"></p>

<script>
document.getElementById("demo").style.display = "none";
</script>
```

[문제] Use the `eventListener` to assign an onclick event to the `<button>`element.

```jsx
<button id="demo">Click me1</button>

<script>
document.getElementById("demo").addEventListener("click", myFunction);
</script>
```