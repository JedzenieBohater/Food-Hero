Trzeba pobrac chromedriver (odpowiedni do systemu i wersji Chrome) i umiescic w tym katalogu pod nazwa chromedriver
https://chromedriver.chromium.org/downloads

Dla MacOS trzeba zmienic
System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
na
System.setProperty("webdriver.chrome.driver", "chromedriver");



Pobrany tutaj przykladowy chromedriver jest dla windowsa i chrome v79

