package domain;

import domain.valueobject.CategoryIdFactory;
import domain.valueobject.DataObject;
import domain.valueobject.ProductIdFactory;

public interface DataLoader {
    DataObject execute(CategoryIdFactory categoryIdFactory, ProductIdFactory productIdFactory);
}
