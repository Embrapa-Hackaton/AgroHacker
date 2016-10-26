package hackathon.embrapa.agrohacker.helper;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.model.Prague;

public class PopulatePragueList {

    private ArrayList<Prague> pragues;

    private ArrayList<Prague> populatePraguesList() {

        pragues = new ArrayList<>();
        Prague prague01 = new Prague();
        Prague prague02 = new Prague();
        Prague prague03 = new Prague();
        Prague prague04 = new Prague();
        Prague prague05 = new Prague();

        prague01.setPopularName("Percevejo-marrom");
        prague01.setScientificName("Euschistus heros");
        prague01.setDescription("As ninfas, recém-eclodidas medem 1mm e têm o corpo alaranjado e a cabeça\n" +
                "preta, passam por cinco estádios de desenvolvimento, até se transformarem em adultos; as ninfas\n" +
                "maiores assumem coloração que pode variar de cinza à marrom. Os adultos são de cor marrom-\n" +
                "escura, apresentam dois prolongamentos laterais, em forma de espinhos, próximos à cabeça.\n" +
                "Seus ovos, de cor amarelada, são normalmente depositados nas folhas, em pequenas massas\n" +
                "com cinco a sete ovos. Próximo a eclosão, os ovos apresentam uma mancha\n" +
                "rósea.");
        prague01.setBioecology("A longevidade média do adulto é de 116 dias. As ninfas recém-eclodidas\n" +
                "medem cerca de 1,3 mm e têm o corpo alaranjado e a cabeça preta. Apresentam hábito gregário e\n" +
                "permanecem sobre os ovos até que atinjam o segundo ínstar. As ninfas maiores (terceiro ao\n" +
                "quinto ínstar) apresentam coloração que pode variar de cinza a marrom. Apesar de iniciarem a\n" +
                "alimentação no segundo ínstar, as ninfas do percevejo-marrom causam danos às sementes\n" +
                "apenas a partir do terceiro ínstar, quando atingem tamanho médio de 3,63 mm. Vários fatores\n" +
                "interferem na duração do ciclo biológico desses insetos. Segundo Cividanes (1992) a duração\n" +
                "média de ovo a adulto foi de 28,4 dias a 25 oC. O desempenho reprodutivo das fêmeas de E.\n" +
                "heros pode variar dependendo do tipo de alimento ingerido e se ocorre ou não interrupção da\n" +
                "alimentação e/ou troca do alimento de ninfa para adulto.");
        prague01.setDamage("Os danos são causados pelos adultos e ninfas ao alimentarem-se das vagens e\n" +
                "grãos causando perdas de rendimentos e afetando a qualidade da semente. Esta espécie provoca\n" +
                "menos sintomas de retenção foliar, em comparação com o percevejo-verde e o percevejo-verde-\n" +
                "pequeno.");
        prague01.setLifeCycleStage("adulto");
        prague01.setPhotoPath(null);
        pragues.add(prague01);

        prague02.setPopularName("Lagarta falsa-medideira");
        prague02.setScientificName("Chrysodeixis includens");
        prague02.setDescription("As lagartas são comumente denominadas falsas-medideiras, por se deslocarem\n" +
                "como que medindo palmos, são de cor verde-clara com listras longitudinais brancas e pontuações\n" +
                "pretas. No seu último estádio larval, atinge 40 a 45 mm de comprimento e a transformação para a\n" +
                "fase de pupa ocorre sob uma teia, em geral, na face ventral das folhas. Essa lagarta pode ser\n" +
                "confundida com a Rachiplusia nu que é mais frequente no Sul do Brasil. Os adultos apresentam\n" +
                "asas dispostas em forma inclinada e, principalmente, as mariposas recém emergidas, apresentam\n" +
                "manchas prateadas brilhantes na parte central do primeiro par de asas. Os adultos também são\n" +
                "muito semelhantes aos de R. nu.");
        prague02.setBioecology("A longevidade dos adultos é de aproximadamente de 15 a 18 dias. O\n" +
                "acasalamento ocorre, mormente, entre 22h e 4h sendo muito importante nesse processo a\n" +
                "emissão do feromônio sexual pelas fêmeas. Posteriormente, as fêmeas depositam os ovos\n" +
                "individualmente e de preferência na superfície inferior das folhas de soja. Em condições de\n" +
                "temperatura e umidade favoráveis (usualmente em condições controladas de criações de\n" +
                "laboratório), cada fêmea oviposita, em média, 700 ovos durante a sua vida. Contudo, variações no\n" +
                "potencial reprodutivo de C. includens têm sido observadas entre 500 a 1.300 ovos por fêmea.\n\n" +
                "De três a cinco dias após a oviposição, as lagartas, que normalmente passam por seis ínstares,\n" +
                "eclodem. Shour e Sparks (1981) observaram variação de cinco a sete ínstares, com\n" +
                "predominância de seis ínstares (cerca de 92%), quando as lagartas se alimentaram de dieta\n" +
                "artificial. A duração desse estágio larval varia de 13 a 20 dias, e o período de ovo à emergência do\n" +
                "adulto varia de 27 a 34 dias.\n\n" +
                "Quando ainda pequenas (primeiro ao terceiro ínstar), as lagartas selecionam folhas novas, com\n" +
                "baixo teor de fibras, enquanto que lagartas mais desenvolvidas tornam-se menos exigentes,\n" +
                "quando passam a se alimentar de folhas mais velhas e mais fibrosas. No Brasil, lagartas\n" +
                "pequenas assim como grandes de C. includens têm sido frequentemente encontradas\n" +
                "alimentando-se do terço inferior das plantas e de folhas tenras de ramos secundários de soja e\n" +
                "algodão.\n\n" +
                "O primeiro e segundo ínstar apenas raspam as folhas, enquanto que a partir do terceiro ínstar a\n" +
                "lagarta consegue fazer furos nelas; elas deixam, entretanto, as nervuras centrais e laterais\n" +
                "intactas, proporcionando aspecto característico de folhas rendilhadas.\n\n" +
                "Os surtos de C. includens parecem ser maiores em agroecossistemas onde a soja e o algodão\n" +
                "são cultivados nas proximidades. Estudos realizados em Louisiana (EUA) constataram um\n" +
                "aumento na longevidade, oviposição e frequência de cópulas, quando foi fornecido o néctar das\n" +
                "flores de algodoeiro para adultos de C. includens. Isso em parte pode explicar o maior índice\n" +
                "populacional de C. includens em soja, quando existe área de algodão nas proximidades. Situação\n" +
                "semelhante pode ocorrer no Brasil Central, com a sobreposição de áreas de cultivo de soja e\n" +
                "algodão.");
        prague02.setDamage("Reduz a área foliar da soja e consequentemente a produtividade. O consumo total\n" +
                "médio de folhas de soja por lagartas de C. includens relatado na literatura é bastante variável,\n" +
                "sendo encontrados valores de 64 cm2 a 200 cm2. As lagartas consomem o parênquima foliar\n" +
                "deixando as nervuras, conferindo aos folíolos aspecto rendilhado. Esta espécie é de difícil\n" +
                "controle, quando comparada com a lagarta-da-soja. Com manejo inapropriado de suas\n" +
                "populações, há relatos de resistência a inseticidas.");
        prague02.setLifeCycleStage("adulto");
        prague02.setPhotoPath(null);
        pragues.add(prague02);

        prague03.setPopularName("Percevejo-verde-pequeno");
        prague03.setScientificName("Piezodorus guildinii");
        prague03.setDescription("As ninfas recém-eclodidas do percevejo-verde-pequeno são avermelhadas e\n" +
                "passam por diferentes fases. No inicio do desenvolvimento, apresentam as cores preta e\n" +
                "vermelha, assumindo, posteriormente, coloração esverdeada com manchas pretas e rosadas no\n" +
                "abdômen nos estádios finais, quando medem cerca de 8mm. Os adultos são percevejos de cor\n" +
                "verde amarelada com, aproximadamente, 10mm de comprimento. Apresentam uma listra\n" +
                "transversal marrom avermelhada, na parte dorsal do tórax, próximo à cabeça. Os ovos são pretos,\n" +
                "em forma de barril, colocados em fileiras pareadas, com 10 a 20 ovos por massa, que geralmente,\n" +
                "são colocados sobre as vagens de soja.");
        prague03.setBioecology("As ninfas recém-eclodidas possuem comportamento gregário, permanecendo\n" +
                "próximas à postura completando o desenvolvimento de ovo a adulto em torno de 24,4 dias. Os\n" +
                "parâmetros biológicos de P. guildinii podem variar dependendo do tipo de alimento e, se ocorre ou\n" +
                "não, troca no alimento de ninfas para adultos. Por exemplo, para percevejos alimentados com\n" +
                "vagens de soja, o número de posturas por fêmea pode ser de aproximadamente três, comparados\n" +
                "a 37 quando são alimentados com algumas espécies de Indigofera sp. O número total de ovos por\n" +
                "fêmea pode variar de 28 em soja a 200 em I. hirsuta e 500 em I. truxillensis. A longevidade dos\n" +
                "adultos pode variar de 50 dias em soja a 90 dias em algumas espécies de anileira.");
        prague03.setDamage("Sugam as vagens, atingindo os grãos de soja. Apresenta maior potencial de dano,\n" +
                "com acentuada capacidade de provocar retenção foliar, quando comparada aos percevejos mais\n" +
                "comuns da cultura da soja. Além disso, P. guildinii apresenta comportamentos de inserção e\n" +
                "retirada dos estiletes que podem causar maior lesão às paredes celulares, em comparação às\n" +
                "outras espécies. Sabe-se que o modo de inserção e a retirada dos estiletes estão associados a\n" +
                "diferentes graus de destruição de tecidos.");
        prague03.setLifeCycleStage("ovo");
        prague03.setPhotoPath(null);
        pragues.add(prague03);

        prague04.setPopularName("Lagarta-da-soja");
        prague04.setScientificName("Anticarsia gemmatalis");
        prague04.setDescription("A lagarta-da-soja, na fase larval, passa por seis ínstares. A lagarta pequena (até\n" +
                "10 mm) geralmente apresenta cor verde e possui quatro pares de propernas no abdômen, duas\n" +
                "delas vestigiais. Com isso, se locomove medindo palmos e, muitas vezes, são confundidas com\n" +
                "lagartas pequenas das falsas-medideiras. As lagartas maiores do que 15 mm podem ser\n" +
                "encontradas tanto nas formas verdes como escuras e apresentam três linhas longitudinais\n" +
                "brancas no dorso e quatro pares de propernas abdominais, além de um terminal. Os adultos são\n" +
                "mariposas de cor variável, do cinza-claro ao marrom-escuro, mas tendo sempre presente uma\n" +
                "linha diagonal de cor marrom-canela, unindo as pontas do primeiro par de asas. Na face inferior\n" +
                "do segundo par de asas, apresenta pequenos círculos brancos, próximos da margem externa da\n" +
                "asa. Ovipositam durante a noite, ovos individualizados e de cor verde claro, colocados\n" +
                "principalmente na face inferior das folhas, mas também nos pecíolos e ramos da soja. As lagartas\n" +
                "eclodem em três dias e passam a se alimentar de folhas.");
        prague04.setBioecology("As maiores porcentagens de acasalamento entre os adultos de A. gemmatalis\n" +
                "ocorrem entre 20 °C e 30 °C, pois nessas temperaturas a fecundidade não é afetada pelo número\n" +
                "de cópulas. Em condições ótimas de ambiente (usualmente condições de laboratório), as\n" +
                "mariposas de A. gemmatalis podem depositar mais que 400 ovos/fêmea durante sua vida.\n" +
                "Fêmeas cujas lagartas foram alimentadas com folhas de soja, em laboratórios, produziram até\n" +
                "1.265 ovos. Em condições de campo, Magrini et al. (1999) observou o número médio de ovos por\n" +
                "fêmea de A. gemmatalis de 73,5 ± 5,0 (média ± erro padrão) ovos, em observações de seis anos\n" +
                "consecutivos em diferentes cultivares de soja. Desses ovos, apenas 31,1 ± 4,9 foram viáveis.\n\n" +
                "A atividade de voo de A. gemmatalis inicia-se por volta das 22h, seguindo até o amanhecer, com\n" +
                "maior ocorrência entre as 2h e 4h. Os ovos são colocados isoladamente ou de forma agrupada\n" +
                "nas folhas ou hastes das plantas, sendo a maior proporção depositada na parte média e inferior\n" +
                "das plantas de soja. Após um período variando de 2,2 a 3,9 dias (dependendo da temperatura), as\n" +
                "lagartas irão eclodir desses ovos.\n\n" +
                "Lagartas de A. gemmatalis podem ter de cinco a sete ínstares larvais, sendo seis ínstares o mais\n" +
                "comum. A duração de cada ínstar pode variar em decorrência de vários fatores, como a\n" +
                "temperatura, a planta hospedeira e a qualidade do alimento. O desenvolvimento larval de A.\n" +
                "gemmatalis alimentando-se de mucuna-rajada foi de 20 a 38 dias, enquanto, alimentando-se de\n" +
                "soja, o período de ovo a adulto foi entre 29 e 33 dias. Contudo, em condições climáticas de campo\n" +
                "no Brasil, essa variação na soja foi entre 15,1 a 19,4 dias.\n\n" +
                "Logo após, a lagarta em pré-pupa caminha para a parte inferior da planta e constrói uma câmara\n" +
                "pupal sob folhas secas na superfície do solo ou, mais frequentemente, até dois centímetros de\n" +
                "profundidade. Dessas pupas irão emergir as mariposas, que acasalam na primeira noite após a\n" +
                "emergência, iniciando a oviposição três a quatro dias depois, sendo que o pico de postura ocorre\n" +
                "ao redor do quinto dia de vida do adulto.");
        prague04.setDamage("Desfolham a soja reduzindo a área foliar da soja e consequentemente a\n" +
                "produtividade. No terceiro estádio, as lagartas já provocam perfurações nas folhas, mas deixam as\n" +
                "nervuras centrais e laterais intactas. O consumo foliar é muito pequeno nos três primeiros estádios\n" +
                "(lagartas até 10 mm). Do quarto ao sexto estádio, as lagartas consomem mais de 95% do total de\n" +
                "consumo foliar, que é de 100 a 120 cm2 por lagarta. Em altas populações, se não controlado,\n" +
                "esse inseto pode provocar desfolhas elevadas (> 30%), causando perdas de produtividade da\n" +
                "cultura.");
        prague04.setLifeCycleStage("lagartas/ninfas grandes");
        prague04.setPhotoPath(null);
        pragues.add(prague04);

        prague05.setPopularName("Lagarta-das-vagens");
        prague05.setScientificName("Spodoptera eridania");
        prague05.setDescription("As lagartas apresentam a linha por baixo dos espiráculos interrompida ou perde\n" +
                "sua intensidade na parte lateral. As manchas triangulares do primeiro segmento abdominal são\n" +
                "grandes e aproximadamente de igual tamanho até as do 8o segmento abdominal. Os adultos\n" +
                "apresentam a asa anterior com traço curto no sentido longitudinal na base da margem posterior.\n" +
                "Essa mancha pode ser apagada em espécimes mais velhos. Observa-se também uma mancha\n" +
                "arredondada, negra, mas geralmente apagada ou pode estar modificada em um traço longo que\n" +
                "se estende até a margem da asa. Comparativamente, em relação a S. albula, S. eridania\n" +
                "apresenta uma tonalidade geral bronzeada nas asas anteriores.");
        prague05.setBioecology("");
        prague05.setDamage("Além de atacar as vagens causa desfolha em soja e algodão, semelhante as demais\n" +
                "espécies do gênero Spodoptera.");
        prague05.setLifeCycleStage("pupa");
        prague05.setPhotoPath(null);
        pragues.add(prague05);

        return pragues;
    }

    public void populatePragueData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        ArrayList<Prague> pragues = populatePraguesList();

        values.put("popularName", pragues.get(0).getPopularName());
        values.put("scientificName", pragues.get(0).getScientificName());
        values.put("description", pragues.get(0).getDescription());
        values.put("bioecology", pragues.get(0).getBioecology());
        values.put("damage", pragues.get(0).getDamage());
        values.put("lifeCicleStage", pragues.get(0).getLifeCycleStage());
        values.put("photoPath", pragues.get(0).getPhotoPath());
        db.insert("Prague", null, values);
        values.put("popularName", pragues.get(1).getPopularName());
        values.put("scientificName", pragues.get(1).getScientificName());
        values.put("description", pragues.get(1).getDescription());
        values.put("bioecology", pragues.get(1).getBioecology());
        values.put("damage", pragues.get(1).getDamage());
        values.put("lifeCicleStage", pragues.get(1).getLifeCycleStage());
        values.put("photoPath", pragues.get(1).getPhotoPath());
        db.insert("Prague", null, values);
        values.put("popularName", pragues.get(2).getPopularName());
        values.put("scientificName", pragues.get(2).getScientificName());
        values.put("description", pragues.get(2).getDescription());
        values.put("bioecology", pragues.get(2).getBioecology());
        values.put("damage", pragues.get(2).getDamage());
        values.put("lifeCicleStage", pragues.get(2).getLifeCycleStage());
        values.put("photoPath", pragues.get(2).getPhotoPath());
        db.insert("Prague", null, values);
        values.put("popularName", pragues.get(3).getPopularName());
        values.put("scientificName", pragues.get(3).getScientificName());
        values.put("description", pragues.get(3).getDescription());
        values.put("bioecology", pragues.get(3).getBioecology());
        values.put("damage", pragues.get(3).getDamage());
        values.put("lifeCicleStage", pragues.get(3).getLifeCycleStage());
        values.put("photoPath", pragues.get(3).getPhotoPath());
        db.insert("Prague", null, values);
        values.put("popularName", pragues.get(4).getPopularName());
        values.put("scientificName", pragues.get(4).getScientificName());
        values.put("description", pragues.get(4).getDescription());
        values.put("bioecology", pragues.get(4).getBioecology());
        values.put("damage", pragues.get(4).getDamage());
        values.put("lifeCicleStage", pragues.get(4).getLifeCycleStage());
        values.put("photoPath", pragues.get(4).getPhotoPath());
        db.insert("Prague", null, values);
    }

}
