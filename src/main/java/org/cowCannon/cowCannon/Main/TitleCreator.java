package org.cowCannon.cowCannon.Main;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;

public class TitleCreator {
    private String mainText;
    private String subText;

    public TitleCreator(String mainText, String subText) {
        this.mainText = mainText;
        this.subText = subText;
    }



    public void showMyTitle(final @NonNull Audience target, NamedTextColor color) {
        final Component mainTitle = Component.text(mainText).color(color);
        final Component subtitle = Component.text(subText).color(color);

        // Creates a simple title with the default values for fade-in, stay on screen and fade-out durations
        final Title title = Title.title(mainTitle, subtitle);

        // Send the title to your audience
        target.showTitle(title);
    }

    public void showMyTitleWithDurations(final @NonNull Audience target, Long fadeDuration, Long fadeIn, Long fadeOut) {
        final Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(fadeDuration),
                Duration.ofMillis(fadeOut));
        // Using the times object this title will use 500ms to fade in, stay on screen for 3000ms and then fade out for 1000ms
        final Title title = Title.title(Component.text(mainText), Component.text(subText), times);

        // Send the title, you can also use Audience#clearTitle() to remove the title at any time
        target.showTitle(title);
    }
}
