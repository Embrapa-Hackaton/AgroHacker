package hackathon.embrapa.agrohacker.helper;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class PopulatePredatorList {

    private ArrayList<NaturalPredator> predators;

    public ArrayList<NaturalPredator> PopulatePredatorList() {

        predators = new ArrayList<>();
        NaturalPredator predator01 = new NaturalPredator();
        NaturalPredator predator02 = new NaturalPredator();
        NaturalPredator predator03 = new NaturalPredator();

        predator01.setPopularName("Trichogramma");
        predator01.setScientificName("Trichogramma spp.");
        predator01.setDescription("Os insetos adultos do gênero Trichogramma podem ser reconhecidos pelo seu tamanho diminuto,\n" +
                "variando de 0,2 mm a 1,5 mm de comprimento, além dos tarsos tri-segmentados, corpo compacto,\n" +
                "sem constrição entre meso e metassoma. Trichogramma spp. tem coloração fosca, cutícula\n" +
                "fracamente esculturada, flagelo antenal com 2 a 9, normalmente 3 a 7, segmentos e nervura pós-\n" +
                "imaginal, normalmente, ausente. Usualmente, a disposição das cerdas das asas anteriores é em\n" +
                "linhas distintas. Ainda, as fêmeas de Trichogramma podem ser separadas de todos os demais\n" +
                "tricogramatídeos por apresentar uma nervação sigmoide e nervura RS1 na asa anterior, antena\n" +
                "com dois segmentos funiculares e clava com um segmento. Os machos, além das diferenças na\n" +
                "asa anterior, também podem ser diferenciados dos demais machos da família pela genitália. Por\n" +
                "causa do diminuto tamanho e da similaridade morfológica dentro da família Trichogrammatidae, a\n" +
                "identificação das diferentes espécies tem sido difícil e deve ser sempre feita por taxonomista\n" +
                "especialista no gênero.\n\n" +
                "As espécies da família Trichogrammatidae são cosmopolitas e compreendem aproximadamente\n" +
                "650 espécies, distribuídas em 80 diferentes gêneros. Trichogramma é o maior e mais importante\n" +
                "gênero dessa família, com aproximadamente 210 espécies descritas. São insetos exclusivamente\n" +
                "endoparasitoides de ovos, com inúmeros hospedeiros, principalmente da Ordem Lepidoptera.\n\n" +
                "O desenvolvimento desse inseto é dividido nas fases de ovo, larva e pupa que ocorrem\n" +
                "obrigatoriamente dentro do ovo hospedeiro e levam 72, 120 e 24 horas, respectivamente, para\n" +
                "completar o desenvolvimento, quando mantidos a 25 oC. A fase adulta é a única de vida livre, com\n" +
                "uma longevidade média em torno de 10 dias (25 oC), quando se alimenta principalmente de néctar\n" +
                "ou “honewdew” presente nas plantas.");
        predator01.setImportance("Parasitam ovos de diferentes espécies de lepidópteros impedindo a\n" +
                "eclosão das lagartas e assim evitando os seus danos.");
        predator01.setPhotoPath(null);
        predators.add(predator01);

        predator02.setPopularName("Telenomus podisi");
        predator02.setScientificName("Telenomus podisi");
        predator02.setDescription("São micro-himenópteros da família Platygastridae que se desenvolvem inteiramente dentro do ovo\n" +
                "hospedeiro, sendo sua ocorrência registrada desde o Centro-Oeste até o extremo sul do Brasil. Os\n" +
                "adultos são pequenos insetos, conhecidos como vespinhas de cerca de 1 mm de comprimento.\n\n" +
                "A forma adulta do parasitoide é a única que tem vida livre e se alimenta de néctar. Esses insetos\n" +
                "são parasitoides solitários que completam o desenvolvimento de ovo a emergência dos adultos\n" +
                "em cerca de 10 a 12 dias. Passam pelas fases de ovo, larva, pré-pupa e pupa, no interior do ovo\n" +
                "hospedeiro.\n\n" +
                "Para T. podisi a 25 oC, a duração foi de 17, 105 e 120 horas para os estádios de ovo, larva e pupa,\n" +
                "respectivamente. O desenvolvimento é, externamente, perceptível pela mudança na coloração dos\n" +
                "ovos do percevejo-hospedeiro. Ovos de coloração clara como os de E. heros e N. viridula\n" +
                "(coloração amarela) e Dichelops spp. (Hemiptera: Pentatomidae) (coloração verde), quando\n" +
                "parasitados, mudam para a cor cinza, correspondendo à fase de larva, posteriormente para o\n" +
                "castanho e, na fase de pupa, tornam-se pretos, a mesma cor dos adultos do parasitoide.\n\n" +
                "Os adultos emergem através de um orifício circular, cortado no opérculo do ovo; os machos\n" +
                "emergem 1 a 2 dias antes das fêmeas para garantir a cópula, que ocorre logo após a sua\n" +
                "emergência. As populações de T. podisi apresentam predominância numérica de fêmeas em\n" +
                "condições naturais.\n\n" +
                "As fêmeas de T. podisi apresentam alto potencialreprodutivo com uma fecundidade média de e\n" +
                "211,0 ovos, em ovos de E. heros sendo eles, normalmente, ovipositados na primeira semana de\n" +
                "vida da fêmea desse inimigo natural");
        predator02.setImportance("Parasitam ovos de diferentes espécies de percevejos com preferência\n" +
                "por ovos do percevejo-marrom (E. heros) impedindo a eclosão das ninfas e assim evitando os\n" +
                "danos dessa praga.");
        predator02.setPhotoPath(null);
        predators.add(predator02);

        predator03.setPopularName("percevejo predador geocoris");
        predator03.setScientificName("Geocoris spp.");
        predator03.setDescription("O reconhecimento dos insetos do gênero Geocoris é bastante simples. São\n" +
                "hemípteros bem pequenos, medindo cerca de 3 mm a 4 mm de comprimento e 1 mm a 2 mm de\n" +
                "largura, de corpo ovalado e cor negra. A característica mais marcante do gênero é a presença dos\n" +
                "olhos proeminentes, que os levam a ser conhecidos como big-eyed bugs. Além dessa\n" +
                "característica, suas antenas apresentam ligeiro alargamento até a porção apical. Suas ninfas\n" +
                "passam por cinco estádios de desenvolvimento, em um período que pode variar de 40 a 100 dias\n" +
                "para atingir a fase adulta. Os adultos podem viver até aproximadamente 70 dias em condições\n" +
                "controladas de laboratório.");
        predator03.setImportance("A importância desse predador na cultura da soja deve-se a sua\n" +
                "ocorrência comum e generalizada em todo o território nacional em razão, principalmente, da sua\n" +
                "boa adaptação a uma ampla faixa de temperatura variando de 16 oC a 38 oC..\n\n" +
                "Entre suas presas está uma grande gama de pequenos insetos-praga que pode variar desde a\n" +
                "mosca-branca, Bemisia spp. (Hemiptera: Aleyrodidae), lagartas pequenas de primeiro ou segundo\n" +
                "ínstar, além de ácaros e ovos de diversas outras pragas\n\n" +
                "Pesquisas realizadas por Corrêa-Ferreira e Moscardi (1985) demonstraram que Geocoris sp.\n" +
                "possui a capacidade de consumo de até nove ovos de A. gemmatalis por dia.");
        predator03.setPhotoPath(null);
        predators.add(predator03);

        return predators;
    }

    public void populatePredatorData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        ArrayList<NaturalPredator> predators = PopulatePredatorList();

        values.put("popularName", predators.get(0).getPopularName());
        values.put("scientificName", predators.get(0).getScientificName());
        values.put("description", predators.get(0).getDescription());
        values.put("importance", predators.get(0).getImportance());
        values.put("photoPath", predators.get(0).getPhotoPath());
        db.insert("Predator", null, values);
        values.put("popularName", predators.get(1).getPopularName());
        values.put("scientificName", predators.get(1).getScientificName());
        values.put("description", predators.get(1).getDescription());
        values.put("importance", predators.get(1).getImportance());
        values.put("photoPath", predators.get(1).getPhotoPath());
        db.insert("Predator", null, values);
        values.put("popularName", predators.get(2).getPopularName());
        values.put("scientificName", predators.get(2).getScientificName());
        values.put("description", predators.get(2).getDescription());
        values.put("importance", predators.get(2).getImportance());
        values.put("photoPath", predators.get(2).getPhotoPath());
        db.insert("Predator", null, values);
    }
}
