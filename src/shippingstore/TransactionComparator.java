package shippingstore;

import java.util.Comparator;

/**
 * This class performs the sorting for the list of packages.
 * Packages are sorted by tracking numbers
 */
public class TransactionComparator implements Comparator<PackageOrder>{

    @Override
    public int compare(PackageOrder o1, PackageOrder o2) {
       return o1.getTrackingNumber().compareTo(o2.getTrackingNumber());
    }
}
