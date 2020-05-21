Descriptors Java API examples
=============================

This repository contains Java Examples to use APIs in the `com.chemaxon.descriptors` 
package. 


Getting started
---------------

  * When you have a [JChem](https://chemaxon.com/products/jchem-engines) distribution 
    [downloaded](https://chemaxon.com/products/jchem-engines/download), point 
    the build to the contained `lib/jchem.jar`:
    
    ``` bash
    ./gradlew -PcxnJchemJar=../jchem/lib/jchem.jar runSimilarityExample
    ```

  * ChemAxon [public repository (`hub.chemaxon.com`)](https://docs.chemaxon.com/display/docs/Public+Repository) can 
    also be used to retrieve dependencies. Make sure your [ChemAxon pass](https://pass.chemaxon.com/login) email address
    is available and you acquire Public Repository API key from <https://accounts.chemaxon.com/my/settings>.
    
    ``` bash
    ./gradlew -PcxnHubUser=<YOUR_PASS_EMAIL> -PcxnHubPass=<YOUR_HUB_API_KEY> runSimilarityExample
    ```
   
   
API docs
--------

JAVA API docs can be foand at the [JChem Base API documentation](https://apidocs.chemaxon.com/jchem/doc/dev/java/api/)
site. The most important packages relevant to this example:

  * [`com.chemaxon.descriptors.common`](https://apidocs.chemaxon.com/jchem/doc/dev/java/api/index.html?com/chemaxon/descriptors/common/package-summary.html)
  * [`com.chemaxon.descriptors.fingerprints.cfp`](https://apidocs.chemaxon.com/jchem/doc/dev/java/api/index.html?com/chemaxon/descriptors/fingerprints/cfp/package-summary.html)
  * [`com.chemaxon.descriptors.alignment`](https://apidocs.chemaxon.com/jchem/doc/dev/java/api/index.html?com/chemaxon/descriptors/alignment/package-summary.html)
   
   
    
Licensing
---------

The content of this project (this git repository) is distributed under the Apache License 2.0. Some dependencies of this
project are **ChemAxon proprietary products** which are **not** covered by this license. 
Please note that redistribution of ChemAxon proprietary products is not allowed.