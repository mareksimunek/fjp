# fjp
1. Tvorba překladače zvoleného jazyka

Cílem práce bude vytvoření překladače zvoleného jazyka. Je možné inspirovat se jazykem PL/0, vybrat si podmnožinu nějakého existujícího jazyka nebo si navrhnout jazyk zcela vlastní. Dále je také potřeba zvolit si pro jakou architekturu bude jazyk překládán (doporučeny jsou instrukce PL/0, ale je možné zvolit jakoukoliv instrukční sadu pro kterou budete mít interpret). 

Jazyk musí mít minimálně následující konstrukce:

definice celočíselných proměnných a konstant
přiřazení
základní aritmetiku a logiku (+, -, *, /, AND, OR, negace a závorky)
cyklus (libovolný)
jednoduchou podmínku (if bez else)
definice podprogramu (procedura, funkce, metoda)
Překladač který bude umět tyto základní věci bude hodnocen deseti body. Další body je možné získat na základě rozšíření, každé je za 4 body:

další typ cyklu (for, do .. while, while .. do, repeat .. unitl)
else větev
příkaz GOTO (pozor na vzdálené skoky)
datový typ boolean a logické operace s ním
datový typ real (s celočíselnými instrukcemi)
datový typ ratio (s celočíselnými instrukcemi)
složený datový typ (Record)
pole 
příkazy pro vstup a výstup (read, write - potřebuje vhodné instrukce které bude možné využít)
rozvětvená podmínka (switch, case)
násobné přiřazení (a = b = c = d = 3;)
podmíněné přiřazení (min = (a < b) ? a : b;)
paralelní přiřazení ({a, b, c, d} = {1, 2, 3, 4};)
parametry předávané odkazem
parametry předávané hodnotou
návratová hodnota podprogramu
...
