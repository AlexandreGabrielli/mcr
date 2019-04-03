package model.ticketsType;

/**
 * cette enum represente les différent type de ticket possible
 */
public enum TicketsType {
    ECONOMY {
        @Override
        public double getPriceCoef() {
            return 1;
        }

        @Override
        public double getMilesCoef() {
            return 1;
        }

        @Override
        public String toString() {
            return "Economy";
        }

    },
    BUSINESS {
        @Override
        public double getPriceCoef() {
            return 2;
        }

        @Override
        public double getMilesCoef() {
            return 5;
        }

        @Override
        public String toString() {
            return "Business";
        }
    },
    FIRSTCLASS {
        @Override
        public double getPriceCoef() {
            return 5;
        }

        @Override
        public double getMilesCoef() {
            return 30;
        }

        @Override
        public String toString() {
            return "First class";
        }

    };

    /**
     * @return le coeficient associer au prix en $ du billet
     */
    public abstract double getPriceCoef();

    /**
     * @return le coeficient associer au prix en miles du billet
     */
    public abstract double getMilesCoef();

    public abstract String toString();


}
