/*
 * Copyright 2023 anominy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.anominy.jda.prelude.interaction.context;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

@SuppressWarnings("unused")
public non-sealed interface IDiscordUserContextMenu extends IDiscordContextMenu {

	@Override
	default void onDiscordMessageContextInteractionEvent(MessageContextInteractionEvent event) {
		IDiscordContextMenu.super.onDiscordMessageContextInteractionEvent(event);
	}

	@Override
	void onDiscordUserContextInteractionEvent(UserContextInteractionEvent event);
}