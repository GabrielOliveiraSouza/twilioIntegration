from logging import exception
import requests
from bs4 import BeautifulSoup
 
# def createSoupG1():
#     url= "https://ge.globo.com/futebol/times/sao-paulo/"
#     response = requests.get(url)
#     soup = BeautifulSoup(response.text, 'html.parser')
#     return soup;

# def getNewsG1():
#         soup = createSoupG1()
#         noticias= soup.findAll("a", {"class": "feed-post-link"})
#         for noticia in noticias:
            
#             print(noticia.string)    

# getNewsG1();

# # CRAWLER DO PLACAR DE FUTEBOL
def autenticacaoPlacar():
    url = 'https://www.placardefutebol.com.br/jogos-de-hoje'
    r = requests.get(url)
    soup = BeautifulSoup(r.text, 'html.parser')
    return soup


def jogos():
    print("API PLACAR AO VIVO ")
    soup = autenticacaoPlacar()
    # campeonatos = soup.find(id="livescore").find_all(class_="container content")
    campeonatos = soup.find('div', {'id': 'livescore'}).find_all('div', {'class': 'container content'})
    todos_jogos = []
    for campeonato in campeonatos:
        jogos = campeonato.find_all('a')
        for jogo in jogos:
            if jogo != 0 and jogo != "0":    
                jogo = dados_jogo(jogo)
                todos_jogos.append(jogo)
   
    arquivo = open("/home/gabriel/eclipse-workspace/jogos.txt", "a")
    arquivo.truncate(0)
    arquivo.write(str(todos_jogos))

def dados_jogo(jogo):
    url = 'https://www.placardefutebol.com.br'
    jogo_link = '{0}{1}'.format(url, jogo['href'])
    campeonato =  jogo['href'].split('/')[1]
    time_casa = ""
    time_fora = ""
    gols_casa = 0
    gols_fora = 0
    status_jogo = ""
    
   
    if jogo.find('h5', {'class': 'text-right team_link'}) != None:
            time_casa = jogo.find('h5', {'class': 'text-right team_link'})
            time_casa = time_casa.string

    try:
       gols_casa = jogo.find('div',{'class':'match-score'}).find('span',{'class':'badge'}).string
    except AttributeError:
        gols_casa = 0

    try:
        gols_fora = jogo.find_all(class_='match-score')[1].find(class_='badge').string
    except IndexError:
        gols_fora  = 0
    except AttributeError:
        gols_fora  = 0
        
    try:
       time_fora = jogo.find('h5', {'class': 'text-left team_link'}).string
    except AttributeError:
        return 0
    
    try:
        status_jogo = jogo.find('div', {'class': 'status'}).find('span', {"class": "status-name"}).string
    except AttributeError:
        return 0

   
    
    time_fora = time_fora.replace("'", "")
    time_casa = time_casa.replace("'", "")





    return( {"time_casa": time_casa, "gols_casa": gols_casa, "time_fora": time_fora, "gols_fora": gols_fora, "status_jogo": status_jogo, "campeonato":campeonato})
    
    
def getCampeonato(camp):
    try:
        return camp.find('div', {'class' : 'match-card-league-name'})
    except AttributeError:
        return "";

jogos()