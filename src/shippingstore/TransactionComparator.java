package shippingstore;

import java.util.Comparator;

public class TransactionComparator implements Comparator<PackageOrder>{

    @Override
    public int compare(PackageOrder o1, PackageOrder o2) {
       return o1.getTrackingNumber().compareTo(o2.getTrackingNumber());
    }
}
