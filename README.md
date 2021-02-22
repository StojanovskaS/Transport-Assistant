# Проект Transport Assistant

*Овој проект е веб страна која е создадена со цел да го олесни патувањето на сите корисници низ нашата земја. 
Идејата е добиена благодарение на се поголемиот број на групи на социјалните мрежи во кои обични граѓани бараат превоз од /до одреден град.Целта на страната е корисниците да немора да го трошат своето време во барање на објави на социјалните мрежи во различни групи, туку се да им е достапно на лесен и едноставен начин.*

 ### Почетната страна ја добивате на :  http://localhost:8045/  или на http://localhost:8045/home 
До оваа страна имаат пристап сите корисници.И навигацијата може да им даде пристап само до Најави се делот.
Најави се е страна која е достапна на localhost:8045/login на неа може да се најавите ако преходно имате креирано акаунт односно се имате Регистрирано во Регистрирај се делот.За одјавување се користи Одјави се од навигацијата.
Се најавувате со вашето корисничко име и лозинката,ако ја згрешите ви дава Bad Credentials и повторно Ве редиректитра на /login.
По успешна најава повторно Ве носи на localhost:8045/home, но сега имате пристап до Мои Постови,Област во Македонија,Договори се делот.
 
 ### Дома страната:
Во зависност од тоа дали при регистрација сте се регистрирале како Админ или Обичен Корисник оваа страна нуди различни функционалности.
Админ Корисникот има можност да Ги уредува(Edit копче ) и да ги Брише(Delete копче)  сите постови без разлика дали се негови или не.
Обичен Корисник ја нема оваа привилегија.Oстанатиот дел и за двата типа на корисници е исто т.е можат да додаваат нова објава и да покажат интерес со притискање на ми се допаѓа иконката,доколку се премислат својот “like” можат да го останат со притискање на не ми се допаѓа иконката.
	Додади или Едитирај страната: http://localhost:8045/home/add или http://localhost:8045/home/id/edit
На оваа страна се пристапува со притискање на Edit копчето или Објави нов пост копчето.
На неа има дел во кој се кажува од кој град се тргнува, односно од кој град го почнува патувањето корисникот, се дава опис: дел кој е наменет да му им се даде на корисниците допоилнителна иформација, односно доколку корисникот поминува низ некои градови ,а воедно тие градови не се негова крајна станица ,а може да однесе луѓе до нив, се наведуваат тука. Исто така се наведува и  времето во кое корисникот може да бие присутен во чат собата (Договори се делот) ,се со цел управувачот на возилото да може да се договори со заинтересираните патници за од каде да ги земе и слично т.е да се разјаснат деталите. Исто така во овој дел корисникот може да ги наведе и слободните места кои ги има во возилото. Потоа се става и цената по човек како и датумот на патувањето кој преставува календар.Потоа доаѓа делот кој всушност ја одредува категоријата во која спаѓа постот ,категорија која е битна за Во која облат од Македонија се изведува патувањето т.е се користи за филтрирање на објавите во однос на областа. Во овој дел корисникот треба да селектира низ кои делови од Македонија поминува (Централна,Западна,Источна) и да ја наведе крајната дестинација.
### Област во Македонија страната :
http://localhost:8045/istocna , http://localhost:8045/centralna  , http://localhost:8045/zapadna 
Всушност оваа страна се пристапува со избор на еден од областите од нашата земја :Централна,Западна,Источна.
Овој дел е наменет за да им се олесни на корисниците да можат да се фокусираат само на објавите кои спаѓаат само во областа на Македонија во која сакаат тие да одат, како и да можат полесно да видат низ кои градови поминува објавувачот и полесно да ја исфилтрираат почетната станица доколку им е потребно.Во овој дел имам имплементирано т.е Пребарување по Почетна станица и Крајна станица.Исто така на корисниците им се дозволуваат основните функционалности од почетната страна(Дома)  без можноста за додавање нов пост.
### Мои постови страната: http://localhost:8045/myposts
Дел во кој корисникот може да ги види сите постови кои тој ги има напишано. Во овој дел нему му е дозволено да ги Променува своите постови или да ги Избрише. Опцијата за ми се допаѓа овде ја нема , во /home се гледа само бројот на заинтересирани корисници за постот, а  во Мои постови корисникот може да ги види кориничките имиња на корисниците кои се заинтересирани за неговиот пост т.е кои би требало да ги чека во Договори се –чат собата.
###	Договори се страната: http://localhost:8045/chat
При влез на оваа страна од корисникот повторно се побарува неговото корисничко име се со цел да може да се приклучи во Чатот,односно да се поврзе на endpoint од WebSocket.
Овде е имплементитран live chat ,односно имам имплементирано In Memory WebSocket, на кој корисниците се поврзуваат со серверот и може да си праќаат пораки само доколку и двата се поврзани на системот. На корисник кој е уклучен пред да се уклучи некој од другите корисници,му се дава известување дека корисникот со соодветото име се има вклучено во чат-собата. Кога некој ја напушти собата ,се известуваат приклучените корисници.
