package com.suliborski.appvideo.tests;

import com.suliborski.appvideo.controller.NavigationController;
import com.suliborski.appvideo.controller.SearchController;
import com.suliborski.appvideo.controller.UserController;
import com.suliborski.appvideo.controller.VideoPlayerController;
import com.suliborski.appvideo.model.dao.*;
import com.suliborski.appvideo.model.models.*;
import com.suliborski.appvideo.view.View;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Tests {
    View view = new View();

    UserDAO userModel = new UserDAO();
    VideoDAO videoModel = new VideoDAO();
    TagDAO tagModel = new TagDAO();
    FilterDAO filterModel = new FilterDAO();
    PlaylistDAO playlistModel = new PlaylistDAO();


    @Test
    public void testUserManipulation() {
        userModel.registerUser("name", "surname", "email", "username", "password", "1990-11-11");
        User newUser = userModel.verifyLogin("username", "password");
        assertNotNull(newUser);
        newUser = userModel.getUserByUsername("username");
        assertNotNull(newUser);

        assertEquals(newUser.getName(), "name");
        assertEquals(newUser.getSurname(), "surname");
        assertEquals(newUser.getEmail(), "email");
        assertEquals(newUser.getUsername(), "username");
        assertEquals(newUser.getPassword(), "password");
        assertEquals(newUser.getBirthday(), "1990-11-11");
        assertFalse(newUser.isPremium());

        userModel.setUserPremium(newUser.getId(), true);
        assertTrue(userModel.getUserById(newUser.getId()).isPremium());

        userModel.deleteUser(newUser.getId());
        assertNull(userModel.verifyLogin("username", "password"));
    }

    @Test
    public void testVideoManipulation() {
        videoModel.addVideo("Taco Hemingway - Fiji (prod. Borucci)", "lk70ee3UMA");
        Video newVideo = videoModel.getVideoByUrl("lk70ee3UMA");
        assertNotNull(newVideo);

        newVideo = videoModel.getVideoById(newVideo.getId());
        assertNotNull(newVideo);

        assertEquals(newVideo.getTitle(), "Taco Hemingway - Fiji (prod. Borucci)");
        assertEquals(newVideo.getUrl(), "lk70ee3UMA");

        assertEquals(newVideo.getViews(), 0);
        videoModel.addViewToVideo(newVideo.getId());
        newVideo = videoModel.getVideoById(newVideo.getId());
        assertEquals(newVideo.getViews(), 1);

        videoModel.deleteVideo(newVideo.getId());
        assertNull(videoModel.getVideoByUrl("lk70ee3UMA"));
    }

    @Test
    public void testPlaylistManipulation(){
        playlistModel.addPlaylist("playlist", 1);
        Playlist newPlaylist = playlistModel.getUserPlaylists(1).get(playlistModel.getUserPlaylists(1).size() - 1);
        assertNotNull(newPlaylist);
        assertEquals(playlistModel.getPlaylistById(newPlaylist.getId()).getId(), newPlaylist.getId());

        assertEquals(newPlaylist.getName(), "playlist");
        assertEquals(newPlaylist.getUserId(), 1);

        Playlist newPlaylistById = playlistModel.getPlaylistById(newPlaylist.getId());
        assertEquals(newPlaylist.getId(), newPlaylistById.getId());

        playlistModel.deletePlaylist(newPlaylist.getId());
        assertNull(playlistModel.getPlaylistById(newPlaylist.getId()));
    }

    @Test
    public void testTagManipulation(){
        tagModel.addTag("tag");
        Tag newTag = tagModel.getAllTags().get(tagModel.getAllTags().size() - 1);
        assertNotNull(newTag);
        assertEquals(tagModel.getTagById(newTag.getId()).getId(), newTag.getId());

        assertEquals(newTag.getName(), "tag");

        Tag newTagById = tagModel.getTagById(newTag.getId());
        assertEquals(newTag.getId(), newTagById.getId());

        tagModel.removeTag(newTag.getId());
        assertNull(tagModel.getTagById(newTag.getId()));
    }

    @Test
    public void testFilterManipulation(){
        filterModel.addFilter("filter", 1, 0, 255);
        Filter newFilter = filterModel.getAllFilters().get(filterModel.getAllFilters().size() - 1);
        assertNotNull(newFilter);
        assertEquals(filterModel.getFilterById(newFilter.getId()).getId(), newFilter.getId());

        assertEquals(newFilter.getName(), "filter");

        Filter newFilterById = filterModel.getFilterById(newFilter.getId());
        assertEquals(newFilter.getId(), newFilterById.getId());

        filterModel.deleteFilter(newFilter.getId());
        assertNull(filterModel.getFilterById(newFilter.getId()));
    }
}