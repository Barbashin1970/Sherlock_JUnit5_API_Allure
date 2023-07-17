<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
<div align="center">
<h2 align="center">Sherlock Test</h2>
  <p align="center">
    Java / Maven / Junit5 / Allure-report
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>

  </ol>
</details>

<!-- ABOUT THE PROJECT -->

**Задание**

Создайте проект с использованием Java, Junit5 и Maven по автоматизированному тестированию приложения. 
Требуется разработать минимально-необходимый набор тестов (позитивных и негативных). 
Все тесты должны быть в одном классе, при этом должна быть возможность запуска из командной строки отдельно для позитивных и негативных.

**Описание тестируемого приложения:**

- Для упрощения задачи предполагается, что приложение не имеет никаких входных параметров. 
- Результатом работы приложения выступает файл в формате json.
- В данном файле случайным образом могут изменяться следующие значения:
- Массив detectives может иметь не менее одного и не более 3-х объектов. 
- При этом поле mainId может случайным образом меняться от 0 до 10
- categoryId принимает значения 1 или 2
- Элемент extra может принимать значение null только для categoryId=2
- Массив extraArray должен иметь минимум один элемент для categoryId=1
- Поле success принимает значение true только если в массиве detectives есть элемент с firstName ="Sherlock"
- пример файла .json в src/main/resources/test.json

Остальными условиями для упрощения задачи можно пренебречь.


**Для запуска тестов из командной строки можно использовать следующие команды:**


mvn clean test -Dtest=ParameterTest#testPositive
mvn clean test -Dtest=ParameterTest#testNegative

Где `ParameterTest` - имя класса с тестами,
`testPositive` и `testNegative` - имена методов тестирования, которые необходимо запустить.

### Built With

* <a href="https://www.java.com/ru/">Java 11</a>
* <a href="https://mvnrepository.com/">Maven</a>
* <a href="https://junit.org/junit5/docs/current/user-guide/">Junit 5</a>
* <a href="https://github.com/allure-framework/">Allure Framework</a>

