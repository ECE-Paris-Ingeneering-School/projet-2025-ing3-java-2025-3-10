package Controleur;

import Vue.StatsVue;


public class StatistiquesControleur {

    private StatsVue statsVue;

    public StatistiquesControleur() {
        this.statsVue = new StatsVue();
    }

    public void afficherStats() {
        statsVue.setVisible(true);

        // ex: créer un camembert
        // statsVue.creerCamembert("Top 5 hébergements", data);
    }
}
