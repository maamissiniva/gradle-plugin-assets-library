package maamissiniva.gradle.plugin.assets;

import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.provider.Property;

abstract public class AssetsLibraryPluginExtension {

    abstract public Property<ConfigurableFileCollection> getFiles();
    // ?? 
    // abstract public Property<Boolean> getAsDefault();
    // Should expose jar stuff such as archive base name.
    
}
