package io.collective.basic;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private final List<Block> chain = new ArrayList<>();

    public boolean isEmpty() {
        
        return chain.isEmpty();
    }

    public void add(Block block) {

        chain.add(block);

    }

    public int size() {
        
        return chain.size();
    }

    public boolean isValid() throws NoSuchAlgorithmException {

        if (chain.isEmpty()) {
            return true;
        }

        System.out.println("only here?? :(");

        for (int i = 1; i < chain.size(); i++) {

            System.out.println("hi here I am!!!!!!!!!!!!!!!!!!!");
            
            Block currBlock = chain.get(i);
            Block prevBlock = chain.get(i - 1);

            System.out.println("Hash in isValid " + chain.get(i));

            // todo - check mined
            if (!isMined(currBlock)) {
                
                return false;
            }

            // todo - check previous hash matches
            if (!currBlock.getPreviousHash().equals(prevBlock.getHash())) {
                
                return false;
            }
            
            // todo - check hash is correctly calculated
            if (!currBlock.getHash().equals(currBlock.calculatedHash())) {
                
                return false;
            }
        }

        if (!Blockchain.isMined(chain.get(chain.size() - 1))) {
            return false;
        }

        return true;
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        
        int nonce = 0;
        Block mined;
        
        do {

            mined = new Block(block.getPreviousHash(), block.getTimestamp(), nonce);
            nonce++;

        } while (!isMined(mined));

        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }
}