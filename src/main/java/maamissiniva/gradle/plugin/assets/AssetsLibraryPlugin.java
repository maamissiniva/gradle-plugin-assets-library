package maamissiniva.gradle.plugin.assets;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.attributes.LibraryElements;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.jvm.tasks.Jar;
import org.gradle.language.base.plugins.LifecycleBasePlugin;

public class AssetsLibraryPlugin implements Plugin<Project> {
    
    @Override
    public void apply(Project project) {
        project.getPluginManager().apply(LifecycleBasePlugin.class);
        
        AssetsLibraryPluginExtension extension = project
                .getExtensions()
                .create("assets", AssetsLibraryPluginExtension.class);
        extension.getFiles().convention(project.files(project.relativeProjectPath("src/main/assets")));

        // zip files from configuration
        TaskProvider<Jar> buildAssetsJar = project.getTasks().register("buildAssetsJar", Jar.class, t -> {
            // t.getArchiveClassifier().set("assets");
            t.getArchiveBaseName().set(project.getName());
            t.getArchiveVersion().set(project.getVersion().toString());
            t.from(extension.getFiles().get());
            t.getDestinationDirectory().set(project.getLayout().getBuildDirectory().dir("libs"));
        });
        
        Configuration assetsDependencyConfiguration = project.getConfigurations().create("assetsDependency", c -> {
        });
        
        // Create a custom consumable configuration.
        // This allows the project to supply the assets variant to other projects
        project.getConfigurations().consumable("assets", c -> {
            c.extendsFrom(assetsDependencyConfiguration);
            // Assign attributes so that consuming projects can match on these
            // The unique attribute allows targeted selection
            c.getAttributes().attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.getObjects().named(LibraryElements.class, "assets"));
        });
        
        project.getArtifacts().add("assets", buildAssetsJar);

        project.getTasks().getByName("build").dependsOn(buildAssetsJar.getName());
    }
    
}
