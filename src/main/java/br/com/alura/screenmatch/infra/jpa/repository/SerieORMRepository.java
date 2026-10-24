package br.com.alura.screenmatch.infra.jpa.repository;

import br.com.alura.screenmatch.infra.jpa.orm.SerieORMEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieORMRepository extends JpaRepository<SerieORMEntity, Integer> {
}
