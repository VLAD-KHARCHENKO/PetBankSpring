# JDBC PETBANK

Tема: "Платежі" 

Клієнт реєструється в системі і має одну або кілька Кредитних карт, кожна з яких відповідає певному Рахунку в системі. Клієнт за допомогою Рахунку може здійснити Платіж.
Платіж має один з двох статусів: 'підготовлений' або 'відправлений'. (За бажанням: реалізувати можливість генерації pdf-звіту про платіж).
Клієнт має особистий кабінет, в якому може переглядати інформацію про свої платежі та рахунки. Необхідно реалізувати можливість сортування:
- платежів:
1) за номером;
2) за датою (від старих до нових, від нових до старих);
- рахунків:
1) за номером;
2) за найменуванням рахунку;
3) за розміром залишку коштів на рахунку.
Клієнт може поповнити або заблокувати один зі своїх рахунків. Для розблокування рахунку клієнт повинен зробити запит до на розблокування.
Адміністратор системи володіє правами:
- блокування / розблокування користувача;
- блокування / розблокування одного з рахунків користувача

## Технології
- DB - h2
- Java version up 8.
- Maven

## Установка і запуск проекту

1.Clone project
2.Run Application
3.Go to link http://localhost:8088/
## Доступний функціонал

- #### Рівень доступу - ADMIN/USER:

1.  Стартова сторінка
2. Вхід/ реєстрація
3. Зміна свого профілю
4. Зміна мови
5. Вихід із системи

- #### Рівень доступу - USER:

6.  Створення платежу
7. Проведення/видалення платежу
8.  Створення картки
9. Поповнення картки
10. Блокування картки
11. Подача запиту на розблокування картки
12. Перегляд та сортування платежів та доступних карток

- #### Рівень доступу - ADMIN:

13.  Редагування/блокування профілю юзера
14.  Блокування карток користувача/ розблокування
15. Розблокування карток користувача

