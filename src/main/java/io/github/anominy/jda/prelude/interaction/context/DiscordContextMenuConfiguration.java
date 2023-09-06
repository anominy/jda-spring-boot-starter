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

import io.github.anominy.jda.prelude.bean.DiscordBean;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DiscordContextMenuConfiguration {
	private final Set<CommandData> discordContextMenuDataSet;
	private final Map<String, IDiscordUserContextMenu> discordUserContextMenuMapByName;
	private final Map<String, IDiscordMessageContextMenu> discordMessageContextMenuMapByName;

	public DiscordContextMenuConfiguration(Set<IDiscordContextMenu> discordContextMenuSet) {
		if (discordContextMenuSet == null) {
			this.discordContextMenuDataSet = null;
			this.discordUserContextMenuMapByName = null;
			this.discordMessageContextMenuMapByName = null;
			return;
		}

		int size = discordContextMenuSet.size();

		Set<CommandData> discordContextMenuDataSet = new HashSet<>(size);
		Map<String, IDiscordUserContextMenu> discordUserContextMenuMapByName = new HashMap<>(size);
		Map<String, IDiscordMessageContextMenu> discordMessageContextMenuMapByName = new HashMap<>(size);

		for (IDiscordContextMenu discordContextMenu : discordContextMenuSet) {
			if (discordContextMenu == null) {
				continue;
			}

			CommandData discordContextMenuData
					= discordContextMenu.initDiscordContextMenuData();

			if (discordContextMenuData == null) {
				continue;
			}

			discordContextMenuDataSet.add(discordContextMenuData);

			String discordContextMenuName = discordContextMenuData.getName();

			if (discordContextMenu instanceof IDiscordUserContextMenu discordUserContextMenu) {
				discordUserContextMenuMapByName.put(discordContextMenuName, discordUserContextMenu);
			} else if (discordContextMenu instanceof IDiscordMessageContextMenu discordMessageContextMenu) {
				discordMessageContextMenuMapByName.put(discordContextMenuName, discordMessageContextMenu);
			}
		}

		this.discordContextMenuDataSet = Collections.unmodifiableSet(discordContextMenuDataSet);
		this.discordUserContextMenuMapByName = Collections.unmodifiableMap(discordUserContextMenuMapByName);
		this.discordMessageContextMenuMapByName = Collections.unmodifiableMap(discordMessageContextMenuMapByName);
	}

	@Bean(DiscordBean.CONTEXT_MENU_DATA_SET)
	public Set<CommandData> getDiscordContextMenuDataSet() {
		return this.discordContextMenuDataSet;
	}

	@Bean(DiscordBean.USER_CONTEXT_MENU_MAP_BY_NAME)
	public Map<String, IDiscordUserContextMenu> getDiscordUserContextMenuMapByName() {
		return this.discordUserContextMenuMapByName;
	}

	@Bean(DiscordBean.MESSAGE_CONTEXT_MENU_MAP_BY_NAME)
	public Map<String, IDiscordMessageContextMenu> getDiscordMessageContextMenuMapByName() {
		return this.discordMessageContextMenuMapByName;
	}
}
