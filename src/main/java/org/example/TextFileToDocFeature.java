package org.example;

import gate.Document;
import gate.Resource;
import gate.creole.AbstractLanguageAnalyser;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.metadata.*;

import lombok.Setter;
import lombok.Getter;

import org.apache.commons.io.IOUtils;

import java.net.URL;

@CreoleResource
public class TextFileToDocFeature extends AbstractLanguageAnalyser {

  @Getter @Setter
  @CreoleParameter
  private URL fileUrl;

  @Getter @Setter
  @CreoleParameter
  private String featureName;

  @Getter @Setter
  @CreoleParameter(defaultValue = "UTF-8")
  private String encoding;

  private String fileContent;

  public Resource init() throws ResourceInstantiationException {
    try {
      fileContent = IOUtils.toString(fileUrl, encoding);
    } catch(Exception e) {
      throw new ResourceInstantiationException(e);
    }
    return this;
  }

  public void execute() throws ExecutionException {
    Document doc = getDocument();
    if(doc != null) {
      doc.getFeatures().put(featureName, fileContent);
    }
  }
}

