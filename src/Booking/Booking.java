package src.Booking;

public class Booking {
    private String head;
    private String body;
    private String footer;

    // Constructor private to force the use of the Builder pattern
    private Booking(Builder builder) {
        this.head = builder.head;
        this.body = builder.body;
        this.footer = builder.footer;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }

    // Static inner class for building the Booking
    public static class Builder {
        private String head;
        private String body;
        private String footer;

        public Builder setHead(String head) {
            this.head = head;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setFooter(String footer) {
            this.footer = footer;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }

    @Override
    public String toString() {
        return "Booking: [Head = " + head + ", Body = " + body + ", Footer = " + footer + "]";
    }
}
