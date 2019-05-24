package gigaherz.lirelent.guidebook.guidebook.templates;

import com.google.common.primitives.Ints;
import gigaherz.lirelent.guidebook.guidebook.IBookGraphics;
import gigaherz.lirelent.guidebook.guidebook.IConditionSource;
import gigaherz.lirelent.guidebook.guidebook.util.Rect;
import gigaherz.lirelent.guidebook.guidebook.drawing.VisualElement;
import gigaherz.lirelent.guidebook.guidebook.elements.Element;
import gigaherz.lirelent.guidebook.guidebook.elements.ElementInline;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.annotation.Nullable;
import java.util.List;

public class TemplateElement extends ElementInline
{
    int index;
    private NamedNodeMap attributes;

    public TemplateElement(boolean isFirstElement, boolean isLastElement)
    {
        super(isFirstElement, isLastElement);
    }

    @Override
    public int reflow(List<VisualElement> list, IBookGraphics nav, Rect bounds, Rect page)
    {
        throw new IllegalStateException("Template elements must not be used directly");
    }

    @Override
    public void parse(IConditionSource book, NamedNodeMap attributes)
    {
        super.parse(book, attributes);

        this.attributes = attributes;

        Node attr = attributes.getNamedItem("index");
        if (attr != null)
        {
            index = Ints.tryParse(attr.getTextContent());

            attributes.removeNamedItem("index");
        }
    }

    @Override
    public String toString(boolean complete)
    {
        return "<template .../>";
    }

    @Override
    public ElementInline copy()
    {
        TemplateElement temp = super.copy(new TemplateElement(isFirstElement, isLastElement));
        temp.index = index;
        temp.attributes = attributes;
        return temp;
    }

    @Override
    public List<VisualElement> measure(IBookGraphics nav, int width, int firstLineWidth)
    {
        throw new IllegalStateException("Template elements must not be used directly");
    }

    @Nullable
    @Override
    public Element applyTemplate(IConditionSource book, List<Element> sourceElements)
    {
        if (index >= sourceElements.size())
            return null;
        Element e = sourceElements.get(index).copy();
        e.parse(book, attributes);
        return e;
    }

    @Override
    public boolean supportsPageLevel()
    {
        return true;
    }
}
