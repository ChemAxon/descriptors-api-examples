package example;

import chemaxon.formats.MolFormatException;
import chemaxon.formats.MolImporter;
import chemaxon.marvin.alignment.AlignmentProperties;
import chemaxon.struc.Molecule;
import com.chemaxon.descriptors.alignment.AtomTyping;
import com.chemaxon.descriptors.alignment.ShapeComparator;
import com.chemaxon.descriptors.alignment.ShapeComparisonResult;
import com.chemaxon.descriptors.alignment.ShapeDescriptor;
import com.chemaxon.descriptors.alignment.ShapeMetrics;
import com.chemaxon.descriptors.alignment.shape.Shape;
import com.chemaxon.descriptors.alignment.shape.ShapeComparatorParameter;
import com.chemaxon.descriptors.alignment.shape.ShapeGenerator;
import com.chemaxon.descriptors.alignment.shape.ShapeGeneratorParameter;
import com.chemaxon.descriptors.fingerprints.cfp.Cfp;
import com.chemaxon.descriptors.fingerprints.cfp.CfpComparator;
import com.chemaxon.descriptors.fingerprints.cfp.CfpGenerator;
import com.chemaxon.descriptors.fingerprints.cfp.CfpParameters;
import com.chemaxon.descriptors.metrics.BinaryMetrics;
import com.google.common.base.Optional;

public class SimilarityExample {

    static Molecule moleculeOf(String s) {
        try {
            return MolImporter.importMol(s, "");
        } catch (MolFormatException ex) {
            throw new IllegalArgumentException(ex);
        }
    }


    public static void main(String [] args) {

        // caffeine, see https://en.wikipedia.org/wiki/Caffeine
        Molecule m1 = moleculeOf("CN1C=NC2=C1C(=O)N(C(=O)N2C)C");
        m1.aromatize();

        // Theophylline, see https://en.wikipedia.org/wiki/Theophylline
        Molecule m2 = moleculeOf("Cn1c2c(c(=O)n(c1=O)C)[nH]cn2");
        m2.aromatize();

        // Using CFP
        final CfpGenerator gen = CfpParameters.createNewBuilder().bondCount(7).bitsPerPattern(3).length(1024).build().getDescriptorGenerator();
        final Cfp fp1 = gen.generateDescriptor(m1);
        final Cfp fp2 = gen.generateDescriptor(m2);
        final CfpComparator comp = gen.forBinaryMetrics(BinaryMetrics.BINARY_TANIMOTO);

        System.out.println("CFP Tanimoto:");
        System.out.println("    similarity:    " + comp.calculateSimilarity(fp1, fp2));
        System.out.println("    dissimilarity: " + comp.calculateDissimilarity(fp1, fp2));
        System.out.println("");

        // Using shape
        ShapeGeneratorParameter.Builder shapeDescriptorParameterBuilder = new ShapeGeneratorParameter.Builder();
        shapeDescriptorParameterBuilder.atomTyping(AtomTyping.CHEMICAL_ATOM_TYPES);
        final ShapeGenerator shapeGenerator = shapeDescriptorParameterBuilder.build().getDescriptorGenerator();

        final Shape sd1 = shapeGenerator.generateDescriptor(m1);
        final Shape sd2 = shapeGenerator.generateDescriptor(m2);

        final ShapeComparatorParameter.Builder shapeComparatorParameterBuilder = new ShapeComparatorParameter.Builder();
        shapeComparatorParameterBuilder.confCount(Optional.of(5));
        shapeComparatorParameterBuilder.flexibilityMode(AlignmentProperties.FlexibilityMode.KEEP_BOTH_FLEXIBLE);
        shapeComparatorParameterBuilder.metrics(ShapeMetrics.SHAPETANIMOTO);


        final ShapeComparator<ShapeDescriptor> shapeComp = shapeGenerator.getShapeComparator(shapeComparatorParameterBuilder.build());

        final ShapeComparisonResult shapeRes = shapeComp.compare(sd1, sd2);

        System.out.println("Shape tanimoto:");
        System.out.println("    similarity:    " + shapeRes.getSimilarity());
        System.out.println("    dissimilarity: " + shapeRes.getDissimilarity());
        // note that shapeRes can supply aligned query and target molecules too




    }
}
