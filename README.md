# Strongly-Connected-Components-Visualizer
Проект для летней учебной практики: реализация, визуализация алгоритма Косарайю и Шарира для нахождения конмпонент сильной связности направленного графа. 

Программа должна визуализировать пошаговое выполнение алгоритма Kasorajy & Sharir. Алгоритм находит все компоненты сильной связанности в графе и раскрашивает их в разные цвета. 

## Спецификация

### Требования к вводу исходных данных
Входные данные могут быть заданы тремя способами:
1. Загружены из файла формата json. Граф хранится в виде:
	
	"NodeNumber" = n
	
	"EdgeNumber" = m
	
	"Edges" = [ [ 1, 2], [2, 3] .. ].
	
	После загрузки воссоздается программой графическое представление графа.

1. Сгенерированы самой программой. Общие данные о графе задаются пользователем в отдельные окнах (количество вершин и ребер в графе), остальное генерируется случайным образом и визуализируются программой. Полученный граф должен быть ориентированным.
   
### Требования к визуализации
Графический интерфейс должен содержать панель управления и холст, на котором будет представлена работа алгоритма.
На панели управления расположены несколько кнопок:
1. Кнопка “Save” – сохраняет построенный граф в файл json.
1. Кнопка “Load”  - загружает граф из файла и строит его на холсте.
1. Кнопка “Generate” – запускает генерацию графа и добавляет его на холст.
1. Кнопка “Algorithm” – запускает алгоритм для нахождения компонент сильной связности графа, выделяя из отличающимися цветами.
1. Кнопка “Delete” – включает режим удаления вершин и ребер при нажатии на них.
1. Кнопка “Clear” – очищает холст.
1. Кнопка "Step" - делает один шаг алгоритма.

Также для генерации графа должны быть созданы два окна, в которых пользователь может указать количество узлов и ребер, опираясь на которые программа сгенерирует граф.

После каждой итерации алгоритма должен отображаться текст, указывающий на изменения в графе ("DFS первоначальный: рассмотрена вершина №x", "Определение компаненкт СС: раскрашена вершина №y"). Просмотренные вершины и пройденные ребра в первом этапе алгоритма будут окрашиваться в серый цвет. Далее цвет вершины будет зависить от компонент сильной связности, ребра в графе Герца должны выделяться жирным шрифтом.

Диаграмма сценариев представлена на рисунке 1, макет интерфейса – на рисунке 2. 	

 <img src="https://github.com/diss03/Strongly-Connected-Components/assets/90706633/db50dc9d-bfe8-4ad7-95f2-05b046bda75f" width="600px" align="center">
 
Рис. 1 – Диаграмма сценариев использования приложения

 <img src="https://github.com/diss03/Strongly-Connected-Components/assets/90706633/b0973dd8-4a91-4bc6-a7f5-7dd0583ae128" width="600px" align="center">
 
Рис. 2 – Макет графического интерфейса приложения
  
### Псевдокод алгоритма
    
	// Инициализация (создание пустого списка)
	L = {}
	
	// Серия поисков в глубину графа G
	while существуют не просмотренные вершины графа do:
		U = первая не просмотренная вершина
		Поиск в глубину: DFS(u)
	
	// Создаем список вершин в порядке увеличения времени выхода Tout
		L = вершины в порядке увеличения времени выхода Tout
	end while
	
	Строим транспонированный граф GT.
	Инвертируем список L (в порядке уменьшения времени выхода Tout)
	
	// Серия поисков в глубину графа GT  в порядке уменьшения времени выхода
	for each (u принадлежит L) и (u – не просмотрена) do:
		Поиск в глубину: DFS(u)
		Очередная компонента сильной связности = множество просмотренных на данном этапе вершин.
	end for


## План разработки и распределение ролей в бригаде

### План разработки

03.07 – Начало работы с отчетом, написание раздела спецификаций, описание ролей участников команды. Согласовние спецификации. Создание Maven-проекта.
	
05.07. – Создание прототипа приложения: реализация структур данных и алгоритма, тестов для структур данных и алгоритма, набросков интерфейса. Создание диаграмм классов и описание сущностей. 
	
07.07 – Создание первой версии приложения: реализация генерации данных, выполение и отображение работы алгоритма, описание тестовых случаев.
	
10.07 – Создание второй версии приложения: реализация корректной работы кнопок, отвечающих за пошаговое выполнение алгоритма; реализация структур данных, отвечающих за пошаговое выполнние алгоритма; реализация тестов для этих структур. Создание диаграммы состояний для описания процесса пошагового исполнения алгоритма, пояснения к диаграмме, описание тестовых случаев, описание интерфейса взаимодействия с пошаговым выполнением алгоритма.
	
12.07 – Сборка проекта в jar-архив, предоставление итогового отчета.

### Распределение ролей в бригаде

Степаненко Денис – покрытие программ тестами, связь интерфейса и внутренних структур данных.
	
Возмитель Влас – визуализация, создание интерфейса программы.
	
Тарасов Константин -  реализация алгоритма и структур данных.

