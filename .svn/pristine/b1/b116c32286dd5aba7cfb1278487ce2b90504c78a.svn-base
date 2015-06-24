__author__ = 'chenghao'


def get_player_name(player_url):
    player_name = player_url.split('/')[2].strip()
    player_names = player_name.split('_')
    format_name = ''
    for name in player_names:
        format_name += name.capitalize() + ' '
    return format_name.strip()