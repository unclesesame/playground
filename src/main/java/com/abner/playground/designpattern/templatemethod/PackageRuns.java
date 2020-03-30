package com.abner.playground.designpattern.templatemethod;

/**
 * Implement template method pattern with Java 8
 */
public interface PackageRuns {

    void execute();

    static void run(PackageRuns... runs){
        for(PackageRuns run: runs){
            run.execute();
        }
    }

    static void runName(String runName){
        System.out.println(runName);
    }
}
