package uk.ac.aber.Game.Treasure;

import uk.ac.aber.Game.CrewCards.CrewCard;

public class TreasureHand {

        public Treasure[] treasures;
        public int totalTreasure;
        private boolean playerHand;
        public TreasureHand() {
            this.treasures = new Treasure[20];
            this.totalTreasure = 0;
        }

        public boolean addTreasure(Treasure T) {
            if (playerHand) {
                if (this.getTotalTreasure() < 2) {

                    this.treasures[totalTreasure] = T;
                    this.totalTreasure++;
                    return true;
                } else {
                    return false;
                }
            }else {
                this.treasures[totalTreasure] = T;
                this.totalTreasure++;
                return true;
                }
            }



        public void giveTreasure(TreasureHand hnd) {
            if (this.totalTreasure > 0) {
                Treasure toTransfer = this.treasures[0];
                if (toTransfer != null) {
                    hnd.addTreasure(toTransfer);
                    this.treasures[0] = null;
                    this.shift(this.treasures);
                }
            }
        }

        public void shift(Treasure[] d) {
            Treasure f = d[0];

            int from = 1;
            for (; from < d.length; from++) {
                d[from - 1] = d[from];
            }

            d[from - 1] = f;
        }

        public void printDebug() {
            System.out.println("---------------------------------------");
            for (int i = 0; i < this.treasures.length; i++) {
                if (this.treasures[i] != null) {
                    System.out.println(this.treasures[i].getValue() + " <  > " + this.treasures[i].getName());
                } else {
                    System.out.println("empty");
                }
            }
            System.out.println("---------------------------------------");
        }

        public int getTotalTreasure() {
            int tmp = 0;
            for (Treasure treasure : this.treasures) {
                if (treasure != null) {
                    tmp++;
                }
            }
            return tmp;
        }



    }





