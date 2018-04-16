/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.cepr0.demo.repo;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Schauder
 */
@ActiveProfiles("test")
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmTest {

	@Autowired
	EntityManager em;


	@Test
	public void find() {
		List resultList = em.createQuery("select distinct p from Parent p left join fetch p.children").getResultList();
		assertThat(resultList).hasSize(3);
	}

	@Test // fails: Expected size:<3> but was:<4>
	public void findAsTuple() {
		List resultList = em.createQuery("select distinct p from Parent p left join fetch p.children", Tuple.class).getResultList();
		assertThat(resultList).hasSize(3);
	}
}
