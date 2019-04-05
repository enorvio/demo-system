package backend.controllers;

import java.io.IOException;
import java.util.Collections;

import javax.swing.tree.DefaultMutableTreeNode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DefaultMutableTreeNodeSerializer extends JsonSerializer<DefaultMutableTreeNode> {

    @Override
    public void serialize(DefaultMutableTreeNode node, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        //gen.writeBooleanField("allowsChildren", node.getAllowsChildren());
        gen.writeObjectField("userObject", node.getUserObject());
        gen.writeObjectField("id", node.toString().hashCode());
        if (node.getChildCount() > 0)
            gen.writeObjectField("children", Collections.list(node.children()));
        // Don't write node.getParent(), it would lead to infinite recursion.
        gen.writeEndObject();
    }
}
