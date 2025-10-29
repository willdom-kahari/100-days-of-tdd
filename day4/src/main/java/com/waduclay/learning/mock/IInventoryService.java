package com.waduclay.learning.mock;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface IInventoryService {
    boolean reserveItem(String productId, int quantity);
    void releaseItem(String productId, int quantity);
}
