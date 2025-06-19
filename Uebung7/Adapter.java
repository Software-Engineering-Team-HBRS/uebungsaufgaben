public class Adapter {
    private Hotelsuche h;

    public Queryresult runQuery(Queryobject d) {
        Suchauftrag s = toAuftrag(d);
        Suchergebnis se = h.sucheHotel(s);
        Queryresult qr = toQuerys(se);
        return qr;
    }

    private Suchauftrag toAuftrag(Queryobject q);
    private Queryresult toQuerys(Suchergebnis s);
}
